# Teletrack

## Overview 

This application enables you to track [Telegram](https://web.telegram.org/) messages in real time: forward new messages to other chats if they satisfy criteria.

It is built on fully functional Telegram client library - [TdLib](https://core.telegram.org/tdlib).

## Installation

You need to have [Java 17](https://www.javatpoint.com/how-to-set-path-in-java) and [Apache Maven](https://www.javatpoint.com/how-to-install-maven) correctly installed.

Implementation of TDLib is OS dependent. TDLib is already built for Windows x64 and Ubuntu 22 systems - in this case the installation process is simplified. If you want to use this application on other OS - you should build the library by yourself.

### Windows x64

Execute commands:

```
cd <repository location>
git clone https://github.com/makskostyshen/teletrack.git
mvn install:install-file -Dfile="teletrack/windows64/tdlib.jar" -DgroupId=com.teletrack -Dar
tifactId=telegram-library -Dversion=1.0 -Dpackaging=jar
robocopy windows64/tdlib tdlib /E
```

### Ubuntu 22

Install [libc++-dev](https://howtoinstall.co/package/libc++-dev) library

Execute commands:

```
cd <repository location>
git clone https://github.com/makskostyshen/teletrack.git
git checkout master-ubuntu
mvn install:install-file -Dfile="teletrack/ubuntu/tdlib-ubuntu.jar" -DgroupId=com.teletrack -Dar
tifactId=telegram-library-ubuntu -Dversion=1.0 -Dpackaging=jar
cp -r ubuntu/tdlib tdlib

```

### Other OS

1. Build TdLib following [instructions](https://tdlib.github.io/td/build.html?language=Java).

2. Clone this repository. Copy ``/tdlib`` folder from built library project to root of created repository.

    ```
    cd <repository location>
    jar cf tdlib.jar -C tdlib/bin .
    mvn install:install-file -Dfile="tdlib.jar" -DgroupId=com.teletrack -Dar
    tifactId=telegram-library -Dversion=1.0 -Dpackaging=jar
    mvn clean install
    java -Djava.library.path="tdlib/bin" -jar "target/teletrack-0.0.1-SNAPSHOT.jar"
    ```
   
## Configuration

1. Create your Telegram Api: https://core.telegram.org/api/obtaining_api_id
2. Add configurations to application.properties file:
   - ``app.telegram.client.apiId`` - id of created API
   - ``app.telegram.client.apiHash`` - hash of created API
   - ``app.messages.forward.group.config-file`` - file name of message forward groups configuration. default: messageTypes.json
   - ``app.messages.forward.group.timezone-id`` - [timezone id](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html). Default: Europe/Kyiv
3. Fill the [message forward groups](#message-forward-group) file configuration

## Launch
Execute commands:

```
mvn clean install
java -Djava.library.path="tdlib/bin" -jar "target/teletrack-0.0.1-SNAPSHOT.jar"
```

## Description

### Message forward group

Message forward group is a base unit of this application, which is responsible for forwarding messages to other chats.
It is configured by user. If telegram message is classified as part of message forward group - forwarding logic will be applied to it.
Message can belong to multiple forward groups.

Message forward group contains:
 - Name - serves as a unique identifier
 - Source Chats Ids - all chats, which can contain units of message forward group
 - Target Chats Ids - chats to forward units of forward message group
 - Criterion - necessary and sufficient rule, applied to message content and used to determine if this message belongs to the forward group

Criterion is a nested structure of combination of predefined type criteria.

Types of criteria:
- `containsText` - is satisfied if message content has given text
- `and` - is satisfied if all nested criteria are satisfied
- `or` - is satisfied if at least one nested criterion is satisfied
- `not` - is satisfied if nested criterion is not satisfied


  Example in JSON:
```
  {
    "name": "test-group",
    "targetChatsIds": [-1002084999999],
    "sourceChatsIds": [-1001181888888, -1014111777777],
    "criterion": {
      "or": [
        {
          "containsText": "text1"
        },
        {
          "containsText": "text2"
        },
        {
          "and": [
            {
              "containsText": "text3"
            },
            {
              "not": {
                "containsText": "text4"
              }
            }
          ]
        }
      ]
    }
  }
```
This message forward group looks for messages in chats with ids `-1001181888888`,
`-1014111777777` and sends them to chat with id `-1002084999999` if they contain `text1` or `text2` or contain 
`text3`, but do not contain `text4`.


There are two ways of configuring message forward groups:
- Before application start - in JSON file, which location is specified in property ``app.messages.forward.group.config-file`` 
- After application start - using [REST endpoints](#rest-endpoints)

### Handling updates

Application handles new message updates from telegram server.
So any message received or sent in real time will be handled.

Also, client's state in relation to messages in specific chat is saved. Unhandled messages,
received during the period of time when client was not active,
will be handled after the start-up of this client.
Reading messages on other devices of the account does not affect this.


## REST endpoints

All endpoints operate in-memory data.
So changes, that have been made using endpoints or data that have been obtained using endpoints will be lost after stopping the application.

### Auth

- `GET: /auth/state` - get current authorization state.
Possible authorization states: `READY`, `WAIT_PHONE_NUMBER`, `WAIT_PHONE_CODE`
Application handles updates only in ready state. Other states indicates invoking appropriate actions

- `POST: /auth/phone` - process phone number for authorization. Body:
  ```
    {
      "phoneNumber": "380xxxxxxxxx"
    }
    ```
- `POST: /auth/code` - process authorization code, received by phone number. Body:
  ```
    {
      "code": "Super secret code"
    }
    ```
### Chats

- `GET: /chats` - get chats. Chat is being saved, if update with this chat is received


### Message forward groups
- `GET: /messages/forward/groups` - get message forward groups
- `POST: /messages/forward/groups` - add message forward group. Body:
  ```
  {
    "name": "work-search-group-5",
    "targetChatsIds": [-1002084684686],
    "sourceChatsIds": [-1002084684682],
    "criterion": {
      "containsText": "text1"
    }
  }
    ```

- `DELETE: /messages/forward/groups{forward-group-name}` - delete message forward group by name

## Licence

Copyright (c) 2023 Maksym Kostyshen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Contact
If you have any questions, feel free to reach me ``makskostyshen@gmail.com``


