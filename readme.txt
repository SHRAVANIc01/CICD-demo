Search APIs:

POST /api/search → add/update search index
GET /api/search → get all
GET /api/search/{id} → get by ID
GET /api/search/keyword/{keyword} → search by keyword
DELETE /api/search/{id} → delete

Recommendation APIs

POST /api/recommendations → add/update recommendation rule
GET /api/recommendations → get all
GET /api/recommendations/{id} → get rule by ID
GET /api/recommendations/user/{userId} → get user-specific recommendations
DELETE /api/recommendations/{id} → delete