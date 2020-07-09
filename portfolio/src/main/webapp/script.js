// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function getComments() {
    var user_limit = document.getElementById("comments-limit").value;
    console.log(user_limit);
    var commentsLimit = parseInt(user_limit);
    if(isNaN(commentsLimit)) {
        commentsLimit = 10;
    }
    fetch("/data?limit="+commentsLimit)
        .then(response => response.json())
        .then(comments => {
            const greetingsElement = document.getElementById('my-comments-container');
            greetingsElement.innerHTML = '';
            for(var comment of comments) {
                console.log(comment);
                const d = new Date(comment.timestamp);
                greetingsElement.appendChild(createListElement("[" + d.toLocaleString() + "]: " + comment.content));
            }
        });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}