CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL
);

CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE user_profile (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE
);
CREATE TABLE topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    author_id BIGINT,
    course_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES user(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

CREATE TABLE answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT,
    creation_date VARCHAR(255) NOT NULL,
    author_id BIGINT,
    solution VARCHAR(255) NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topic(id),
    FOREIGN KEY (author_id) REFERENCES user(id)
);

