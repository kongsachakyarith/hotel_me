CREATE TABLE rooms (
                       room_id BIGSERIAL PRIMARY KEY,       -- auto-increment ID
                       room_name VARCHAR(100) NOT NULL,     -- matches validation: 2–100 chars
                       room_type VARCHAR(50) NOT NULL,      -- type like Deluxe, Standard, etc.
                       price_per_night NUMERIC(10,2) NOT NULL CHECK (price_per_night >= 0.01),
                       availability_status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE'
);

CREATE TABLE bookings (
                          booking_id serial4 primary key,
                          user_id BIGINT NOT NULL,
                          room_id BIGINT NOT NULL,
                          booking_status VARCHAR(20) DEFAULT 'PENDING',
                          booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          check_in_date DATE,
                          check_out_date DATE,
                          FOREIGN KEY (user_id) REFERENCES u_sers(user_id),
                          FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

CREATE TABLE waiting_list (
                              waitlist_id serial4 primary key,
                              user_id BIGINT NOT NULL,
                              room_type_requested VARCHAR(50),
                              request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES u_sers(user_id)
);


INSERT INTO rooms (room_name, room_type, price_per_night, availability_status)
VALUES
    ('Deluxe Suite', 'Deluxe', 120.00, 'AVAILABLE'),
    ('Standard Room', 'Standard', 60.00, 'AVAILABLE'),
    ('Family Room', 'Family', 90.00, 'BOOKED'),
    ('Presidential Suite', 'Luxury', 350.00, 'AVAILABLE');
