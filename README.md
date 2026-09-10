# Backend Spring Boot CRUD API

## Required environment variables

Set these in Render (do not commit real values):

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<database>
SPRING_DATASOURCE_USERNAME=<database-user>
SPRING_DATASOURCE_PASSWORD=<database-password>
FRONTEND_URL=http://localhost:3000
```

Set `FRONTEND_URL` to the deployed Netlify URL when the frontend is deployed.

The application exposes:

- `GET /api/users`
- `POST /api/users/register`
- `POST /api/users/login`
- `DELETE /api/users/{id}`
