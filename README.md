Original App Design Project - README
===

# COLLAB

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
[A social media app which allows creators from different arts to message and connect to work on new projects while also aiding each other to advance in their professions by giving each other advice/introducing them to new connections.]

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Photo & Video / Social
- **Mobile:** Uses camera and it is mobile first experience.
- **Story:** Allows users to show their own artwork and collaborate with other artists.
- **Market:** Anyone that is interested in expanding their business and growing their social-media through collaborating and working with other artists could enjoy this app. Gives users the ability to find other users with similar interests.
- **Habit:** Users can post throughout the day many times.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can create a new profile/Log into existing profile. 
* User (registered) can create new posts for collab
* [Bottom Navigation]: Four fragments with home feed, personal profile, compose picture and discovery feed.
* [Discovery Feed]: Will create a user-specific feed to show them new artists that they are interested in by selecting a list of topics. 

**Optional Nice-to-have Stories**

* [Chat Room] Add additional menu items inside the bottom navigation menu to allow users to communicate with each other.
* [Pull-to-Refresh] Implement pull to refresh on home timeline.
* [Viewing other users profiles] If a user clicks on a profile, they can view all the posts they have.
* User (registered) can follow another user to view on Home feed
* [Home Feed]: Will show friends that you have added in the app and the work they have recently created.


## 2. Navigation

**Tab Navigation** (Tab to Screen)

* Home Feed
* Profile
* Discovery Page
* Compose Page
* Chat Activity

**Flow Navigation** (Screen to Screen)

* Login Screen
	=> Home

* Discovery Page
	=> List of Topics
	=> Creators you interested in collaborating in the topic chosen

* Compose
	=> launch camera and create new post

* User
	=> Profile Page

* Profile Page
  => Posts
  => Compose Page
  
* Chat Page
  => Facebook Messenger
  => Common Chat Room
  
## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src='pictures/wireframe.jpg' title='Video Walkthrough' width='' alt='Video Walkthrough' />

### NAVIGATION WITH STRETCH GOALS
<img src='pictures/stretchWireframe.png' title='Video Walkthrough' width='' alt='Video Walkthrough' />



## Schema

### Models

#### User

| Property           | Type           | Description                                       |
|:------------------ |:-------------- |:------------------------------------------------- |
| objectId           | String         | unique id for the user (default field)            |
| email              | String         | email user has set for account                    |
| username           | String         | display name of the user                          |
| following          | Array\<String> | list of pointers to users they are following      |
| followers          | Array\<String> | list of pointers to users that are following them |
| createdAt          | DateTime       | date when user is created (default field)         |
| userDescription    | String         | description of the user                           |



#### Post

| Property     | Type            | Description                                             |
| ------------ |:--------------- |:------------------------------------------------------- |
| objectId     | String          | unique id for the project                               |
| name         | String          | display name of the project                             |
| description  | String          | description of the project                              |
| picture      | ParseFile       | image of the work they would like to show               |
| user         | Pointer to User | pointer to User that submitted this project             |
| createdAt    | DateTime        | date when user is created (default field)               |


