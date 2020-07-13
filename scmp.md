1.  code review
    1.  one MR 20 comments
2.  complex user workflow
    1.  editor published an article, archiver did some correction. and those correction won't be seen by editor. But if editor change again, the changes will override archiver's change.
3.  Need to ask more about user use case
    1.  editor published an article, v1 with photo a & b, v2 with photo a only. If user want to search article with photo b, which version of article should we return?

# Technical
## Gitlab
1.  pipelink TOCHECK

## Hashicorp Vault
1.  secrets management, work with gitlab
2.  can login with OIDC (Open ID connect) by using Google account

### Reference
https://medium.com/@petertc/openid-connect-a27e0a3cc2ae
## Gsuite

## GRPC
