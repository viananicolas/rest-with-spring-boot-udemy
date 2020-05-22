CREATE TABLE users (
                       [id] bigint NOT NULL IDENTITY,
                       [user_name] varchar(255) DEFAULT NULL,
                       [full_name] varchar(255) DEFAULT NULL,
                       [password] varchar(255) DEFAULT NULL,
                       [account_non_expired] bit DEFAULT NULL,
                       [account_non_locked] bit DEFAULT NULL,
                       [credentials_non_expired] bit DEFAULT NULL,
                       [enabled] bit DEFAULT NULL,
                       PRIMARY KEY ([id]),
                       CONSTRAINT [uk_user_name] UNIQUE  ([user_name])
) ;