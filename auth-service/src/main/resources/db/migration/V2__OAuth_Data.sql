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
    'appUserClient',
    '$2a$04$skVqhOtxYuZ37NNcM/1LAOIV/skAn/sEe9bNKMztn4k8AXlS5ksrG',
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
    'appCommentClient',
    '$2a$04$lH8jU7Hy4ew/eS69hnQWLOxqdAjWiC0LMraYnPosVfwCkmM6xdQqC',
    'commentService',
    'read,write',
    'authorization_code,check_token,refresh_token,password',
    1000000,
    1000000
  );