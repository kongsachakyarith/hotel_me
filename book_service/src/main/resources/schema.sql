CREATE TABLE rooms (
                       room_id  serial4 primary key,
                       room_number VARCHAR(20) UNIQUE NOT NULL,
                       room_type VARCHAR(50),
                       price_per_night DECIMAL(10,2),
                       availability_status VARCHAR(20) DEFAULT 'AVAILABLE'
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
