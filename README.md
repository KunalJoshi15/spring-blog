An APU that has the following constraints is known as the 
restful API
1) CLient Server Architecture -> There is always a client interacting with a server
2) Stateless -> No data should be stored on the server during the processing.
3) Cacheable -> The client should have the ability to store responses in a cache.
4) Uniform Interface -> This constraint indicates a generic interface to manage all the interactions.
5) Layered System -> The server can have multiple layers for implementation
6) Code on Demand -> The constraint is optional. 

URI -> For web based application.
GET - http://localhost:8080/api/posts : 

REST - Sub resources
GET /resources/{resource-id}/sub-resource
POST /resource/{resource-id}/sub-resoirces

/cars/1/drivers
HTTP methods are used for specifying the action on a specified resource.
GET -> To get a collection or single resource.
POST -> Create a new resource
PUT -> To update an existing resource.
DELETE -> To delete a collection or a single resource.

200 OK, 201 Created, 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 500 Internal Server Error.

High Level Requirements for Blog App
1) Posts Management - Create Read Update and Delete.
2) Comments Management -  Create Read Update and Delete comments for Blog
3) Authentication and Authorization - Register Login and Security
4) Category Management - Create, Read Update and Delete.

Mostly while working with the spring based application. Most people prefer to use the 3 layer architecture.
Controller Service and DAO
Spring boot uses hikari datasource.

SimpleJpaRepository internally implements JpaRepository.
The APIs will throw a ResourceNotFoundException whenever the resource is not found with given id.

Whenever we are designing a rest api we need to follow the rest design pattern.

GetMapping is used for creating the get request mapping for fetching all the posts.
By default java uses the PagingAndSortingRepository for getting the data. This is extended by the JpaRepository.

We can even define the sort order to the PageRequest.of(pageSize,pageNo,SortDir)
There is one to Many relationship Post and Comment.
Post has the PostEntity (One To Many) ContentEntity.

JoinColumn annotation is used for specifying the join column.
CascadeType all annotation is used for making changes to the other linked entity as well.