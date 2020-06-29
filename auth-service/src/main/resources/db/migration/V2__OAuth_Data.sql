INSERT INTO
  oauth_client_details (
    client_id,
    client_secret,
    resource_ids,
    scope,
    authorized_grant_types,
    access_token_validity,
    refresh_token_validity
  )
VALUES
  (
    'appUserServiceClient',
    '$2a$04$5NamgB.8FdmT.ie6J0MyuuRPVltEJmaUYVaWbBUraAbLvNiMZtL1G',
    'userService',
    'read,write',
    'authorization_code,check_token,refresh_token,password',
    1000000,
    1000000
  );

INSERT INTO oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types,
    access_token_validity,
    refresh_token_validity)
VALUES
  (
    'appCommentServiceClient',
    '$2a$04$7Krkeyuar098iCfrRebmY.92zYTK6FGx4.VRlHOHQBt3IsRDamZqa',
    'commentService',
    'read,write',
    'authorization_code,check_token,refresh_token,password',
    1000000,
    1000000
  );