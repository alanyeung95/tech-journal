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

Purpose: OAuth 2.0 is an authorization framework that allows third-party services to exchange web resources on behalf of a user. Its primary role is to provide secure delegated access, meaning it allows one service to interact with another on behalf of a user, without revealing the user's credentials to the third-party service.

How It Works: It involves granting a token (instead of credentials) that enables access to a specific set of resources for a given time period. This token is then used by third-party applications to access the resources as permitted by the token's scope.

Use Case: For example, OAuth 2.0 is what allows a photo printing service to access your photos stored in a cloud storage service without you having to share your cloud storage login credentials with the photo service.

### ID Token
Backend & Frontend ref: https://stackoverflow.com/a/38604676
example: 

sign in website X with google G:
1. X request authorization to G
2. G ask user for authorizaton
3. user confirm G
4. G create and send access token to X
5. X could send access token to backend Y 
6. Y could validate the token using G's API
7. G validate token and send result to Y
8. Y could generate another token like jwt that contain user or business info to communicate X
 
### Access Token

The access_token obtained from Google OAuth 2.0 is a key element in the OAuth authorization framework, enabling your application to access Google APIs on behalf of a user.

```
Authorization : Bearer cn389ncoiwuencr
```

ref: https://medium.com/automationmaster/getting-google-oauth-access-token-using-google-apis-18b2ba11a11a

### id token vs access token
https://auth0.com/blog/id-token-access-token-what-is-the-difference/


### Others
- ref: https://www.youtube.com/watch?v=CPbvxxslDTU, https://www.youtube.com/watch?v=THs9QUUXVhk
- npm ref: https://www.youtube.com/watch?v=roxC8SMs7HU&t=0s


## SSO
https://www.youtube.com/watch?app=desktop&v=O1cRJWYF-g4

Purpose: SSO is a user authentication process that allows a user to access multiple applications with one set of login credentials (such as logging in with a single ID and password). This enhances the user experience by reducing password fatigue from different user name and password combinations and speeding up the login process across multiple applications and services.

How It Works: When a user logs in once, the SSO solution authenticates them and then provides this authentication status to all other associated systems.

Use Case: A common example is a corporate environment where a user logs in once and gains access to various systems like email, HR systems, and more without needing to log into each one separately.

### SSO vs Oauth

ref: https://stackoverflow.com/questions/77679120/what-is-the-difference-between-sso-and-oauth

SSO and OAuth are two different protocols that can be used for different purposes.

SSO is a method that allows users to authenticate once and access multiple applications without being prompted to enter their credentials again. This is typically done by an identity provider (IdP) that issues a token to the user, which is then passed to the different applications the user wants to access.

OAuth, on the other hand, is an open standard for authorization (not authentication). It allows users to grant third-party applications access to their resources (e.g. data) without sharing their credentials. OAuth does not provide authentication, but it can be used in conjunction with other protocols, such as SSO, to provide a seamless user experience.

In summary, SSO is used for authenticating users, while OAuth is used for granting access to resources. OAuth can be used as part of an SSO solution, but it is not a replacement for SSO.

# Endpoint Security

Endpoint protection works via a combination of network and device-level defenses. At the network level, the organization may restrict access to the enterprise network based on a device’s compliance with corporate security policies and least privilege. By blocking insecure devices from accessing the corporate network and sensitive resources, the organization restricts its attack surface and enforces its security policies.

## Endpoint Protection Features (Components)
- Anti-Malware: Endpoint protection solutions should detect and prevent infections by viruses, worms, and other malware.

- Behavioral Analytics: Ransomware and other malware variants have unique behaviors that make them detectable without the use of signatures. By monitoring these behaviors, endpoint protection solutions can detect and respond to zero-day attacks.

- Data Encryption: Encryption is the most effective way to protect data against unauthorized access and potential breach. Endpoint security solutions should offer full disk encryption (FDE) and support encryption of removable media.

- URL Filtering: Malicious links are a commonly-used technique in phishing attacks, and inappropriate web usage on corporate devices impedes productivity and puts the company at risk. URL filtering helps prevent these threats by blocking malicious and inappropriate websites.

ref: https://www.checkpoint.com/cyber-hub/threat-prevention/what-is-endpoint-security
