#  Auth Service

- Handles Google login using spring-boot-starter-oauth2-client (Thymeleaf optional).
- Issues tokens (ID Token).
- Provides internal endpoint /internal/token?userId=... to issue the token to other services programmatically.