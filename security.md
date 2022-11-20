# Authentication

## Basic Authentication
由username和password來組成認證碼，例如：
```
username: alice, pw: superman
alice:superman
Base64 encoded: YWxpY2U6c3VwZXJtYW4=
HTTP request header:
Authorization: Basic YWxpY2U6c3VwZXJtYW4=
```

Basic故名思義，是最簡單方便快捷的方法，user甚至不用經過login page來登入。

問題
- Server只能確認帳號密碼，無法控制例如登入時效（session），只能靠browser的cookie expiration
- Attacker可以Decode header並得到username和password
- Replay attack
於是又有了比較複雜一點的方法：Digest。


ref: https://carsonwah.github.io/http-authentication.html

## OAuth 2.0
sign in website X with google G:
1. X request authorization to G
2. G ask user for authorizaton
3. user confirm G
4. G create and send password to X
5. X use password to get access token from G
access token:
```
Authorization : Bearer cn389ncoiwuencr
```

- different website X will have different password
- X with password can only access to limited feature in G

- ref: https://www.youtube.com/watch?v=CPbvxxslDTU, https://www.youtube.com/watch?v=THs9QUUXVhk
- npm ref: https://www.youtube.com/watch?v=roxC8SMs7HU&t=0s
