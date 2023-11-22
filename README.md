# Teletrack

## Overview 
This application enables you to track [Telegram](https://web.telegram.org/) messages in real time and send them to another chat, if they satisfy certain requirements.

Is build using [TdLib](https://core.telegram.org/tdlib).

## Installation

You need to have [Java 17](https://www.javatpoint.com/how-to-set-path-in-java) and [Apache Maven](https://www.javatpoint.com/how-to-install-maven) correctly installed.

Implementation of TdLib OS dependent. As Windows x64 user, I've already built library version for Windows x64. So if have this OS - process of installation will be simplified.

### Windows x64

Execute commands:

```
cd <repository location>
git clone https://github.com/makskostyshen/teletrack.git
mvn install:install-file -Dfile="teletrack/windows64/tdlib.jar" -DgroupId=com.teletrack -Dar
tifactId=telegram-library -Dversion=1.0 -Dpackaging=jar
```

### Other OS

1. Build TdLib following [instructions](https://tdlib.github.io/td/build.html?language=Java).

2. Clone this repository. Copy ``/tdlib`` folder from built library project to root of created repository.

3. Build .jar file from generated classes and create a Maven artifact. Execute:
   
    ```
    cd <repository location>
    jar cf tdlib.jar -C tdlib/bin .
    mvn install:install-file -Dfile="tdlib.jar" -DgroupId=com.teletrack -Dar
    tifactId=telegram-library -Dversion=1.0 -Dpackaging=jar
    ```

## Configuration

1. Create your Telegram Api: https://core.telegram.org/api/obtaining_api_id
2. Add configurations to your ``application.properties``:
   - ``app.telegram.client.apiId`` - id of created API
   - ``app.telegram.client.apiHash`` - hash of created API
   - ``app.telegram.updates.chats.sources.ids`` - chats to track messages from
   - ``app.telegram.updates.chats.target.id`` - chat to send tracked messages
   - ``app.telegram.updates.keywords.require`` - required words to forward message. Forwarding the message requires the presence of at least one of these words.
   - ``app.telegram.updates.keywords.forbid`` - forbidden words to not forward message. If any of them is present, the message will not be forwarded.

## Licence

Copyright (c) 2023 Maksym Kostyshen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Contact
If you have any questions, feel free to reach me ``makskostyshen@gmail.com``


