## cdn

### benefit

1. lower network ladency as servers are closer to the end user
2. improve read performance, as data are cache and serve geographically to user
3. less query to single source of truth

### system-design

static content (e.g. html) store in aws s3 bucket <-> aws cloudfront CDN <-> End user

- restrict s3 bucket access to AWS cloudfront CDN only

<img src="https://blog.tryexponent.com/content/images/cCDIK3TKy4Aye-PhLv2_KqMrkbLUtoUDOm0gwcG3YAAR-Sm0qG1_q7UtNZPaBGYcdHwEw0Ohtlefe-7WDwIiN5NJdynFYGrZEplW8ACFFiB9ULlIJuE8V9xqdbimWQ6FnoRCV5Sp.png"/>
