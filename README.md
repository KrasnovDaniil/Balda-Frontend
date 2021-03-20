Here I want to describe done work:

## Common info
Heroku can deploy project from specified branch of Git repository and only on one language (as I understood).
Heroku requires similar folder structure for Java applications, it means all folders directly are in root directory and `pom.xml` too. The same for JS app - need special structure.   
So I decided to split actual branch `feature-Chat` on 2 branches - `backend-only` and `frontend-only` and deploy each of them separately.

## Frontend 
Host on https://balda-frontend.herokuapp.com.
[Frontend branch](https://github.com/KrasnovDaniil/freelancer_portal/tree/frontend-only).
For now I didn't fix Leo's issues.

## Backend
Host on https://balda-play.herokuapp.com. 
[Backend branch](https://github.com/KrasnovDaniil/freelancer_portal/tree/backend-only)

## How to test
First of all, need go to https://balda-play.herokuapp.com and wait some response - Whitelabel error page, it will turn on backend and application will work.
For test appliation, you need follow this link - https://balda-frontend.herokuapp.com, you'll see join menu, then input name and room, sign in and chat with roommates.  
**Main remark:** due to using free type of Heroku hosting, it hosts site not all the time (18 hours max) and work at economic way - turns off site if it doesn't use for some time, so have to wait about half a minute for turning site on.

## Some notes
In a good way, Heroku suggests to transfer or clone original project on separate repository for hosting. But I just added in original repo 2 branches and host them like if they would be repos.
Further, I'll follow on properly way and allocate repository to each part of application.
