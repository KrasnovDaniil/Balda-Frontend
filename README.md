Here I want to describe done work:

## Common infor
Heroku can deploy project from specified branch of Git repository and only on one language (as I understood).
Heroku requires similar folder structure for Java applications, it means all folders directly are in root directory and `pom.xml` too. The same for JS app - need special structure.   
So I decided to split actual branch `feature-Chat` on 2 branches - `backend-only` and `frontend-only` and deploy each of them separately.

## Frontend 
Locate on balda-frontend.herokuapp.com.
For now I didn't fix Leo's requrements.

## Backend
Locate on [as](balda-play.herokuapp.com). 

## How to test
For test appliation, you need follow this link - balda-frontend.herokuapp.com, you'll see join menu, then input name and room, sign in and chat with roommates.  
**Main remark:** due to using free type of Heroku hosting, it hosts site not all the time (18 hours max) and work at economic way - turns off site if it doesn't use for some time, so have to wait about half a minute for turning site on.

## Some notes
In a good way, Heroku suggests to transfer or clone original project on separate repository for hosting. But I just added in original repo 2 branches and host them like if they would be repos.
Further, I'll follow on properly way and allocate repository to each part of application.