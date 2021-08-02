Umbrella  Sharing Available Network Service
===========================================

## Project Name: USAN
### Spring Boot Framework

서버 : AWS ( Amazon Web Service ) EC2  
DB : My SQL


## Umbrella API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/umb/get/list|GET|전체 우산을 조회합니다  (보관소 정보 제외)|
|/umb/get/umbrellaList/storageList|GET|보관소 전체와 우산 전체를 조회합니다|
|/umb/get/lateDate/day/{umbrella_id}|GET|해당 id 값인 우산의 빌린날과 반납 날짜와의 <br>차이를 비교하여 보냅니다|
|/umb/post/joinProc|POST|우산을 DB에 저장 합니다|umbrellaId:'id'|
|/umb/put/mapping/{umbrellaId}/{rental_period}|PUT|Umbrella의 user_id와 User의 umbrellaId에다가 각각의 <br>지정된 id 값을 할당 rental_period 기간 동안 대여 처리 합니다.|User: 'userId', umbrellaId:'id'<br>rental_period:' '
|/umb/put/return/{umbrella_id}|PUT|Umbrella의 user_id와 User의 umbrella_id에다가 <br> 0 값을 할당하여 반납처리 합니다|User: 'userId', umbrellaId:'id'

## Storage API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/storage/get/list|GET|전체 보관소를 조회합니다
|/storage/post/joinProc|POST|보관소를 DB에 저장합니다|Storage:'storageId'
|/storage/post/{id}/umb/{umb_id}/mapping|POST| 보관소에 우산을 선택하여 DB에 저장합니다|storageId:'id', umbrellaId:'umbrellaId'

## User API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/auth/post/joinProc|POST|DB에 사용자 정보를 등록합니다|User:'username','nickName','password'<br> 'phoneNumber', email

## Inquiry API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/inquiry/get/all/list|GET| DB에 있는 모든 문의글을 가져옵니다|
|/inquiry/get/{id}|GET| 특정 id에 해당하는 문의글을<br> DB에서 가져옵니다|id:'inquiryId'
|/inquiry/get/noanswer/list|GET| DB에서 답변이 완료되지 않은 <br>모든 글을 가져옵니다|
|/inquiry/get/answer/list|GET| DB에서 답변이 완료된 <br>모든 글을 가져옵니다|
|/inquiry/post/save|POST|문의 글을 작성, DB에 저장합니다
|/inquiry/delete/{id}|DELETE|문의 글을 DB에서 삭제 합니다|id:'inquriyId'
|/inquiry/put/{id}|PUT| DB에 있는 특정 id에 해당하는 <br>문의 글을 수정합니다.|id:'inquiryId'
|/inquiry/put/{id}/answer/{userId}|PUT| 관리자(ADMIN)이 작성한 답변 글을<br> inquiry 모델에 저장합니다.|id:'inquiryId',userId:'userId(ADMIN)'




