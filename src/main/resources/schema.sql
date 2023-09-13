-- -----------------
-- 创建用户表
-- -----------------
DROP TABLE IF EXISTS User;
CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                      user_name VARCHAR(64) NOT NULL UNIQUE COMMENT '用户名',
                      pass_word VARCHAR(128) NOT NULL COMMENT '密码',
                      create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- -----------------
-- 创建问题表
-- -----------------
DROP TABLE IF EXISTS Question;
CREATE TABLE Question (
                          question_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '问题ID',
                          user_id INT NOT NULL COMMENT '用户ID',
                          title VARCHAR(128) NOT NULL UNIQUE COMMENT '问题标题',
                          content TEXT NOT NULL COMMENT '问题内容',
                          create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '问题发布时间',
                          FOREIGN KEY (user_id) REFERENCES User(user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='问题表';

-- -----------------
-- 创建答案表
-- -----------------
DROP TABLE IF EXISTS Answer;
CREATE TABLE Answer (
                        answer_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '答案ID',
                        user_id INT NOT NULL COMMENT '用户ID',
                        question_id INT NOT NULL COMMENT '问题ID',
                        content TEXT NOT NULL COMMENT '答案内容',
                        create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '答案发布时间',
                        FOREIGN KEY (user_id) REFERENCES User(user_id),
                        FOREIGN KEY (question_id) REFERENCES Question(question_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='答案表';

-- -----------------
-- 创建回复表
-- -----------------
DROP TABLE IF EXISTS Reply;
CREATE TABLE Reply (
                       reply_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '回复ID',
                       user_id INT NOT NULL COMMENT '用户ID',
                       answer_id INT NOT NULL COMMENT '答案ID',
                       content TEXT NOT NULL COMMENT '回复内容',
                       create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复发布时间',
                       FOREIGN KEY (user_id) REFERENCES User(user_id),
                       FOREIGN KEY (answer_id) REFERENCES Answer(answer_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='回复表';