CREATE TABLE IF NOT EXISTS payments (
	id UUID PRIMARY KEY,
	name VARCHAR(255),
	value NUMBER,
	due_date DATE,
	payment_date DATE,
	fees NUMBER,
	fine NUMBER,
	delay NUMBER
);