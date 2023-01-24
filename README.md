# Umbrella  Sharing Available Network Service

# 2021 융소 공모전. 우산 무인 대여 시스템 프로젝트

## 프로젝트 소개
```
  1. 갑작 스런 우천상황이 다가오면 불필요한 우산구매가 생기며 이는 환경 오염 문제까지 이어진다.  
  2. 언제 어디서나 우산을 편리하게 대여할 수 있는 대여소를 마련하자.
  3. 무인으로 운영하여 비용과 관리 업무를 줄이자
```
### Web Deploy : http://usan-env.eba-wvps3pmt.ap-northeast-2.elasticbeanstalk.com/
### 소개 영상 : https://www.youtube.com/watch?v=sEy0c9RgW60


## 팀원 소개   
| `아두이노`강정모 | `아두이노`공혜란 | `아두이노`정채은 | `PM`강두한 
| `프론트`고영훈 | `프론트`이채린 | `프론트`김인애 | `백엔드`고지훈 |
|------|------|------|------|
|  <img style="width:200px; height:200px" src = "https://github.com/kyh0576.png"> | <img style="width:200px; height:200px" src = "https://github.com/Lee-chaerin.png"> | <img style="width:200px; height:200px" src = "https://github.com/dlsdo1101.png"> | <img style="width:200px; height:200px" src = "https://github.com/emibgo2.png"> |
|  [ryuyh2000](https://github.com/kyh0576) | [Lee-chaerin](https://github.com/Lee-chaerin) | [dlsdo1101](https://github.com/dlsdo1101) | [emibgo2](https://github.com/emibgo2) 

## 기술 스택
### ***frontend***
```
- JavaScript
- HTML
- CSS
- Thymeleaf
```

### ***backend***
```
- Spring boot
- Spring Security
- JPA
- MySQL
- Heroku
- Beanstalk
```

## Umbrella API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/umb/list|GET|전체 우산을 조회합니다  (보관소 정보 제외)|
|/umb/umbrellaList/storageList|GET|보관소 전체와 우산 전체를 조회합니다|
|/umb/lateDate/day/{umbrella_id}|GET|해당 id 값인 우산의 빌린날과 반납 날짜와의 <br>차이를 비교하여 보냅니다|
|/umb/joinProc|POST|우산을 DB에 저장 합니다|umbrellaId:'id'|
|/umb/mapping/{umbrellaId}/{rental_period}|PUT|Umbrella의 user_id와 User의 umbrellaId에다가 각각의 <br>지정된 id 값을 할당 rental_period 기간 동안 대여 처리 합니다.|User: 'userId', umbrellaId:'id'<br>rental_period:' '
|/umb/return/{umbrella_id}|PUT|Umbrella의 user_id와 User의 umbrella_id에다가 <br> 0 값을 할당하여 반납처리 합니다|User: 'userId', umbrellaId:'id'

## Storage API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/storage/list|GET|전체 보관소를 조회합니다
|/storage/joinProc|POST|보관소를 DB에 저장합니다|Storage:'storageId'
|/storage/{id}/umb/{umb_id}/mapping|POST| 보관소에 우산을 선택하여 DB에 저장합니다|storageId:'id', umbrellaId:'umbrellaId'

## User API 명세서
URL|METHOD|Description|Params
:---:|:---:|:---:|:---:|
|/auth/joinProc|POST|DB에 사용자 정보를 등록합니다|User:'username','nickName','password'<br> 'phoneNumber', email
|/auth/id/check|POST|DB에 사용자 정보를 등록합니다|User:'username','nickName','password'<br> 'phoneNumber', email
