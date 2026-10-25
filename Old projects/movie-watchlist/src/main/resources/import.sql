-- Users
INSERT INTO users (username, email, password, created_at) VALUES ('john_doe', 'john@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES ('admin_user', 'admin@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', CURRENT_TIMESTAMP);
INSERT INTO users (username, email, password, created_at) VALUES ('jane_smith', 'jane@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', CURRENT_TIMESTAMP);

-- Media (fără discriminator, doar datele comune)
INSERT INTO media (title, description, release_year, rating, created_at, updated_at) VALUES ('Inception', 'A thief who steals corporate secrets', 2010, 8.8, CURRENT_DATE, CURRENT_DATE);
INSERT INTO media (title, description, release_year, rating, created_at, updated_at) VALUES ('The Matrix', 'A computer hacker learns the truth', 1999, 8.7, CURRENT_DATE, CURRENT_DATE);
INSERT INTO media (title, description, release_year, rating, created_at, updated_at) VALUES ('Interstellar', 'Explorers travel through a wormhole', 2014, 8.6, CURRENT_DATE, CURRENT_DATE);
INSERT INTO media (title, description, release_year, rating, created_at, updated_at) VALUES ('Breaking Bad', 'A teacher turns to cooking meth', 2008, 9.5, CURRENT_DATE, CURRENT_DATE);
INSERT INTO media (title, description, release_year, rating, created_at, updated_at) VALUES ('Stranger Things', 'A boy disappears mysteriously', 2016, 8.7, CURRENT_DATE, CURRENT_DATE);

-- Movies (id-urile TREBUIE să fie aceleași ca în media!)
INSERT INTO movie (id, duration, director, has_sequel) VALUES (1, 148, 'Christopher Nolan', false);
INSERT INTO movie (id, duration, director, has_sequel) VALUES (2, 136, 'The Wachowskis', true);
INSERT INTO movie (id, duration, director, has_sequel) VALUES (3, 169, 'Christopher Nolan', false);

-- Series (id-urile TREBUIE să fie aceleași ca în media!)
INSERT INTO series (id, seasons, total_episodes, series_status) VALUES (4, 5, 62, 'COMPLETED');
INSERT INTO series (id, seasons, total_episodes, series_status) VALUES (5, 4, 34, 'ONGOING');

-- Genres
INSERT INTO media_genres (media_id, genre) VALUES (1, 'ACTION');
INSERT INTO media_genres (media_id, genre) VALUES (1, 'THRILLER');
INSERT INTO media_genres (media_id, genre) VALUES (1, 'SCI_FI');
INSERT INTO media_genres (media_id, genre) VALUES (2, 'SCI_FI');
INSERT INTO media_genres (media_id, genre) VALUES (2, 'ACTION');
INSERT INTO media_genres (media_id, genre) VALUES (3, 'SCI_FI');
INSERT INTO media_genres (media_id, genre) VALUES (3, 'DRAMA');
INSERT INTO media_genres (media_id, genre) VALUES (4, 'DRAMA');
INSERT INTO media_genres (media_id, genre) VALUES (4, 'THRILLER');
INSERT INTO media_genres (media_id, genre) VALUES (5, 'HORROR');
INSERT INTO media_genres (media_id, genre) VALUES (5, 'DRAMA');
INSERT INTO media_genres (media_id, genre) VALUES (5, 'SCI_FI');


-- WatchList Items (user_id: 1=john_doe, 2=admin_user, 3=jane_smith)
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (1, 1, 'COMPLETED', 9.0, '2024-01-15', '2024-01-16', '2024-01-15');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (1, 4, 'WATCHING', 8.5, '2024-02-01', NULL, '2024-02-01');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (1, 3, 'PLAN_TO_WATCH', NULL, NULL, NULL, '2024-03-10');

INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (2, 2, 'COMPLETED', 8.5, '2024-01-20', '2024-01-20', '2024-01-20');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (2, 5, 'WATCHING', 9.0, '2024-02-15', NULL, '2024-02-15');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (2, 1, 'COMPLETED', 9.5, '2024-03-01', '2024-03-01', '2024-03-01');

INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (3, 5, 'COMPLETED', 8.8, '2024-01-10', '2024-02-20', '2024-01-10');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (3, 2, 'WATCHING', 7.5, '2024-03-05', NULL, '2024-03-05');
INSERT INTO watchlist_items (user_id, media_id, status, personal_rating, start_date, finish_date, added_at) VALUES (3, 3, 'PLAN_TO_WATCH', NULL, NULL, NULL, '2024-03-15');