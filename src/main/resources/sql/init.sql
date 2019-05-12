CREATE TABLE `blog`
(
    `id`         int(11)                          NOT NULL AUTO_INCREMENT,
    `title`      varchar(100) COLLATE utf8mb4_bin NOT NULL,
    `content`    mediumtext COLLATE utf8mb4_bin   NOT NULL,
    `createTime` datetime                         NOT NULL,
    `modifyTime` datetime                         NOT NULL,
    `digest`     varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

CREATE TABLE `tag` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `tag` varchar(30) NOT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `nickname` varchar(15) COLLATE utf8mb4_bin NOT NULL,
                           `content` varchar(256) COLLATE utf8mb4_bin NOT NULL,
                           `create_time` datetime NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `blog_tag` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `tagId` int(11) NOT NULL,
                            `blogId` int(11) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

CREATE TABLE `comment_blog` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `blog_id` int(11) NOT NULL,
                                `comment_id` int(11) NOT NULL,
                                PRIMARY KEY (`id`),
                                KEY `blog_id_key` (`blog_id`),
                                KEY `comment_id_key` (`comment_id`),
                                KEY `comment_blog` (`comment_id`,`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;