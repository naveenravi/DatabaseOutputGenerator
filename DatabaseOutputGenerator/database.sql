CREATE TABLE  `suppliers` (
`supplier_id` INT( 10 ) NOT NULL ,
`name` VARCHAR( 255 ) NOT NULL ,
`code` VARCHAR( 255 ) NOT NULL ,
`telephone_number` VARCHAR( 32 ) NOT NULL ,
`email_address` VARCHAR( 255 ) NOT NULL ,
PRIMARY KEY (  `supplier_id` )
);

CREATE TABLE 'supplier_parts' (
  'supplier_id' INT NOT NULL,
  'part_name' VARCHAR(255) NOT NULL,
   'part_code' VARCHAR(32) NOT NULL,
    'cost_in_cents' SMALLINT(5) NOT NULL
);

select s.supplier_id,s.name, sp.part_name
from supplier_parts sp 
INNER JOIN suppliers s 
ON s.supplier_id = sp.supplier_id

select sp.part_name, sp.cost_in_cents
from supplier_parts sp
order by cost_in_cents DESC
