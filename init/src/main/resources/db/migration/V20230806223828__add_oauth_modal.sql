INSERT INTO `redbook`.`oauth2_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`,
                                                  `client_secret_expires_at`, `client_name`,
                                                  `client_authentication_methods`, `authorization_grant_types`,
                                                  `redirect_uris`, `post_logout_redirect_uris`, `scopes`,
                                                  `client_settings`, `token_settings`)
VALUES ('d84e9e7c-abb1-46f7-bb0f-4511af362ca6', 'password-client-id', '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC', NULL, '密码模式授权平台', 'client_secret_basic',
        'refresh_token,authorization_password', '', 'http://127.0.0.1:9000/', 'openid,profile',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",7200.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",3600.000000000]}');

INSERT INTO `redbook`.`oauth2_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`,
                                                  `client_secret_expires_at`, `client_name`,
                                                  `client_authentication_methods`, `authorization_grant_types`,
                                                  `redirect_uris`, `post_logout_redirect_uris`, `scopes`,
                                                  `client_settings`, `token_settings`)
VALUES ('3eacac0e-0de9-4727-9a64-6bdd4be2ee1g', 'web-client-id', '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC', NULL, 'web平台', 'client_secret_basic',
        'refresh_token,authorization_code', 'http://spring-oauth-client:9001/login/oauth2/code/messaging-client-oidc',
        'http://127.0.0.1:9000/', 'openid,profile',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');