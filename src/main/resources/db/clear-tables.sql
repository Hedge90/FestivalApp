ALTER TABLE days ALTER COLUMN id RESTART;
ALTER TABLE artists ALTER COLUMN id RESTART;
ALTER TABLE organizers ALTER COLUMN id RESTART;

DELETE FROM days;
DELETE FROM artists;
DELETE FROM organizers;
