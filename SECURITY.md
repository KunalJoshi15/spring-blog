Migration and clean up:
Spring boot version 3.0.x and Java17 or later.
Change package name from javax to jakarta
Change the hibernate dialect
Drop and create new customer database.

We just need to add the spring boot starter security for implementing the security for the spring boot application.
By default the spring security provides the security for all the endpoints which are available in the spring boot.
http
.csrf().disable()
.authorizeRequests()
.anyRequest()
.authenticated()
.and()
.httpBasic();
this enables the basic auth functionality in the spring application.

Using the @PreAuthorize annotation we can specify the user which can access a particular rest api.
In order to have a database authentication we need to have two separate entities as User and Role.
In case of a many to many mapping there is a third table which is usually created. It is used for 
establishing an association between the current tables which are existing.

In case of database related role configuration we will be needing the customuserdetailservice