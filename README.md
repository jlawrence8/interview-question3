Forum Application
=================


This is a sample Forum application. Run it (using `mvn spring-boot:run`) or your favorite IDE.
It has the below 4 APIs:

### Post new question: `http://localhost:5000/questions`
#Example:
[http://localhost:5000/questions](http://localhost:5000/questions)

Input:
```json
{
  "author": "Daniel",
  "message": "Message text"
}
```
Sample response will be following with HTTP Status 201:
```json
{
  "id": 1,
  "author": "Daniel",
  "message": "Message text",
  "replies": 0
}
```

### Post a reply to a message: `http://localhost:5000/questions/{questionId}/reply`
#Example:
[http://localhost:5000/questions/1/reply](http://localhost:5000/questions/1/reply)

Input:
```json
{
  "author": "Reply author",
  "message": "Message reply text"
}
```
Sample response will be following with HTTP Status 201:
```json
{
  "questionId": 1,
  "id": 5,
  "author": "Reply author",
  "message": "Message reply text"
}
```

### Get Question by ID: `http://localhost:5000/questions/{questionId}`, 
Example:
[http://localhost:5000/questions/1](http://localhost:5000/questions/1)


Sample response will be following with HTTP Status 200:
```json
{
  "id": 1,
  "author": "Daniel",
  "message": "Message text",
  "replies": [
    {
       "id": 5,
       "author": "Reply author",
       "message": "Message reply text"
    },
    ...
  ]
}
```

### Get a list of questions: `http://localhost:5000/questions`
Example:
[http://localhost:5000/questions](http://localhost:5000/questions)

Sample response will be following with HTTP Status 200:
```json
[
    {
      "id": 1,
      "author": "Daniel",
      "message": "Message text",     
      "replies": 0
    },
    ...
]
```

## Design Considerations:
* There is no API to update question. Observation: If question can be updated then the replies to the question will become invalid and so this functionality is not required.
* There is no API to update the replies.
* There is no API to delele a question or reply.
* Same questions can be added again (especially by the same author). This can be fixed as an enhancement.
* Posting question and reply can take additional parameters at the moment. However, the additional parameters will not be considered and so there is no immediate threats.
* No pagination added for 'Get List of Questions' API and 'Get Question by ID' API. 

## Documents
Documents are available under 'docs' folder.

