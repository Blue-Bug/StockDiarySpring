# StockDiary
### SpringFramework로 만든 간단한 주식일지입니다.
- 간단한 회원기능과 일지 CRUD를 수행합니다.
- Repository는 인터페이스로 현재 회원과 일지는 메모리에 저장하도록 구현하였습니다.

### REST API
- MEMBER 관련  

|METHOD|URI|DESCRIPTION|
|------|---|-----------|
|GET|/|로그인 상태에 따라 홈화면을 조회합니다.|
|GET|/members/create|회원 가입 폼을 가져옵니다.|
|POST|/members/create|회원 가입을 수행합니다.|
|GET|/login|로그인 폼을 가져옵니다.|
|POST|/login|로그인을 수행합니다.|
|POST|/logout|로그아웃을 수행합니다.|  

![login](https://user-images.githubusercontent.com/46710160/132814560-5212b497-85e9-4eae-a70b-972969e86455.JPG)

- DIARY 관련

|METHOD|URI|DESCRIPTION|
|------|---|-----------|
|GET|/diaries| 일지 목록을 조회합니다.|
|GET|/diaries/{diaryId}|일지 아이디로 하나를 조회합니다.|  
|GET|/diaries/create|일지 작성 폼을 가져옵니다.|
|POST|/diaries/create|일지를 생성합니다.|
|GET|/diaries/{diaryId}/edit|일지 수정 폼을 가져옵니다.|
|POST|/diaries/{diaryId}/edit|일지를 수정합니다.|
|POST|/diaries/{diaryId}/delete|일지를 삭제합니다.|  

![diary](https://user-images.githubusercontent.com/46710160/132814616-dc30ba31-15ec-4de0-8a18-123e4a86f8f5.JPG)
