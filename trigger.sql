-- Author
-- Brian Torpey
-- 7/8/2019

-- trigger for keeping customer total_points up to date at all times
-- assume redeem and reward points are the same value
-- assume redeem points and reward points are not null

create or replace function update_total_customer_points() returns trigger as
$$
declare
    customer_points float;
    booster_factor  float;
    row_purchase record;

    temp_reward_points float;
    total_purchase_points float := 0;

    -- assume that if a coffee is promoted, it exists in the promotefor table
    is_promoted boolean;

    begin
        customer_points := (
            select total_points
            from customer c1
                join purchase p1
                on c1.customer_id = p1.customer_id
                where p1.purchase_id = new.purchase_id);

        booster_factor := (
                select m1.booster_factor
                from memberLevel m1 join (
                    select memberlevel_id from
                        customer c2 join purchase p2
                        on c2.customer_id = p2.customer_id
                    where p2.purchase_id = new.purchase_id
                    ) tmp1
                on m1.memberlevel_id = tmp1.memberlevel_id
            );

        is_promoted := exists (select * from promotefor pf1 join buycoffee bc1
            on bc1.coffee_id = pf1.coffee_id
            where bc1.purchase_id = new.purchase_id);

        for row_purchase in
            select * from (
                purchase p3 join buycoffee bc2 on p3.purchase_id = bc2.purchase_id)
            where p3.purchase_id = new.purchase_id
        loop

            -- calculate temp reward_points for coffee_id
            -- points of coffee is reward points times booster factor times 2 if promoted
            -- adjust total reward points based off of the purchase and redeem quantities

            temp_reward_points := booster_factor * (select reward_points from coffee coffee1 where coffee1.coffee_id = row_purchase.coffee_id);

            temp_reward_points = row_purchase.purchase_quantity*temp_reward_points - row_purchase.redeem_quantity*temp_reward_points;
            total_purchase_points = total_purchase_points + temp_reward_points;
        end loop;

        if is_promoted is true then
            total_purchase_points = total_purchase_points * 2;
        end if;

        customer_points = customer_points + total_purchase_points;
        update customer set total_points = customer_points where customer_id = (
            select customer_id from purchase p2 where p2.purchase_id = new.purchase_id);

        return new;
    end;
$$ language plpgsql;

drop trigger if exists trig_update_customer_points on buycoffee;
create trigger trig_update_customer_points
    after insert
    on buycoffee
    for each row
execute procedure  update_total_customer_points();


