create table profiles (
                          id   bigint      not null auto_increment,
                          name varchar(20) not null,
                          primary key(id)
);

create table courses (
                         id       bigint      not null auto_increment,
                         name     varchar(30) not null,
                         category varchar(10) not null,
                         primary key(id)
);

create table users (
                       id         bigint       not null auto_increment,
                       name       varchar(30)  not null,
                       email      varchar(30)  not null,
                       password   varchar(100) not null,
                       profile_id bigint       not null,
                       primary key(id),
                       constraint fk_user_profile foreign key(profile_id) references profiles(id)
);

create table topics (
                        id            bigint       not null auto_increment,
                        title         varchar(100) not null,
                        message       varchar(255) not null,
                        creation_date date         not null,
                        status        boolean      not null,
                        author_id     bigint       not null,
                        course_id     bigint       not null,
                        primary key(id),
                        constraint uq_topic_title_message unique (title, message),
                        constraint fk_topic_author foreign key(author_id) references users(id),
                        constraint fk_topic_course foreign key(course_id) references courses(id)
);


create table responses (
                           id            bigint       not null auto_increment,
                           message       varchar(255) not null,
                           topic_id      bigint       not null,
                           creation_date date         not null,
                           author_id     bigint       not null,
                           is_solution   boolean      not null,
                           primary key(id),
                           constraint fk_response_author foreign key(author_id) references users(id),
                           constraint fk_response_topic foreign key(topic_id) references topics(id)
);
