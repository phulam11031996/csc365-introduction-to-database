-- first part
DELIMITER $$ drop trigger if exists addtransaction $$ create trigger addtransaction
after
insert on transaction for each row begin
update card
set balance = balance + new.amount
where cardNumber = NEW.cardNumber;
END $$ DELIMITER;

-- second part
DELIMITER $$ drop trigger if exists updatecard $$ create trigger updatecard before
update on card for each row begin if(new.creditLimit < new.balance) then signal sqlstate '12345';
end if;
END $$ DELIMITER;