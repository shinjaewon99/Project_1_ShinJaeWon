# 🦁 멋쟁이사자처럼 Mini_Project
------------------------------------------
## 멋사 마켓
➡ 작성자 : 신재원

➡ Mail : tlswodnjs99@naver.com

> **프로젝트 개발기간: 2023.06.29 ~ 2023.07.04**

## ▶ 프로젝트 소개
사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 
최종적으로 구매 제안에 대하여 수락할 수 있는 형태의  중고 제품 거래 플랫폼을 구현한 스프링 부트 프로젝트입니다.

---------------------------------------------------

## ▶ Environment
> Language : Java 17

> IDE : Intellij

> Framework : SpringBoot (3.1.1)

> DataBase : SqlLite

> ORM : Hibernate

----------------------------------------------------------

## ▶ DB ERD
![멋사 사진 ERD](https://github.com/likelion-backend-5th/MiniProject_Basic_ShinJaeWon/assets/95893341/84b4645c-56d7-410a-97ea-7872ad3e4b3d)


---------------------------------------------------------------------------

## 👀 프로젝트 요구 사항 
### 1️⃣ 중고 물품 등록 기능
🛠 개발 기간 : 6.29 ~ 7.2 
<details>
<summary> 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>이때 반드시 포함되어야 하는 내용은 제목, 설명, 최소 가격, 작성자이다.</li><br>
  <li>또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.</li><br>
  <li>최초로 물품이 등록될 때, 중고 물품의 상태는 판매중 상태가 된다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 등록된 물품 정보는 누구든지 열람할 수 있다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>페이지 단위 조회가 가능하다.</li><br>
  <li>전체 조회, 단일 조회 모두 가능하다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 등록된 물품 정보는 수정, 삭제가 가능하다.   </summary>
<div markdown="1">
<br>
<ul>
  <li>이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 등록된 물품 정보에 이미지를 첨부할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.</li><br>
  <li>이미지를 관리하는 방법은 자율이다.</li><br>
</ul>
  </div>
</details>

#### ➡ 중고 물품 관리 API 명세서 : https://documenter.getpostman.com/view/22851675/2s946icX5i

----------------------------------------------

### 2️⃣ 중고 물품의 댓글 기능
🛠 개발 기간 : 7.2 ~ 7.3 
<details>
<summary>등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>이때 반드시 포함되어야 하는 내용은 대상 물품, 댓글 내용, 작성자이다.</li><br>
  <li>또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.</li><br>
</ul>
  </div>
</details>
<details>
<summary>등록된 댓글은 누구든지 열람할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>페이지 단위 조회가 가능하다.</li><br>
</ul>
  </div>
</details>
<details>
<summary>등록된 댓글은 수정, 삭제가 가능하다. </summary>
<div markdown="1">
<br>
<ul>
  <li>이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.</li><br>
</ul>
<ul>
  <li>만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다. </li><br>
  <li>답글은 댓글에 포함된 공개 정보이다. </li><br>
</ul>
  </div>
</details>


#### ➡ 중고 물품 댓글 API 명세서 : https://documenter.getpostman.com/view/22851675/2s946icX5k
----------------------------------------------


### 3️⃣ 구매 제안 기능
🛠 개발 기간 : 7.3 ~ 7.4 
<details>
<summary>등록된 물품에 대하여 구매 제안을 등록할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>이때 반드시 포함되어야 하는 내용은 대상 물품, 제안 가격, 작성자이다. </li><br>
  <li>또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다. </li><br>
  <li>구매 제안이 등록될 때, 제안의 상태는 제안 상태가 된다. </li><br>
</ul>
  </div>
</details>
<details>
<summary>구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>대상 물품의 주인은, 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다. <br>
    이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다. </li><br>
  <li>등록한 사용자는, 조회를 위해서 자신이 사용한 작성자와 비밀번호를 첨부해야 한다. <br>
    이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다. </li><br>
</ul>
  </div>
</details>
<details>
<summary>등록된 제안은 수정, 삭제가 가능하다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>이때, 제안이 등록될때 추가한 작성자와 비밀번호를 첨부해야 한다. </li><br>
</ul>
  </div>
</details>

<details>
<summary>대상 물품의 주인은 구매 제안을 수락할 수 있다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.  </li><br>
  <li>이때 구매 제안의 상태는 수락이 된다.</li><br>
</ul>
  </div>
</details>
<details>
<summary>대상 물품의 주인은 구매 제안을 거절할 수 있다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.</li><br>
  <li>이때 구매 제안의 상태는 거절이 된다.</li><br>
</ul>
  </div>
</details>
<details>
<summary>구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>이를 위해서 제안을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.</li><br>
  <li>이때 구매 제안의 상태는 확정 상태가 된다. </li><br>
  <li>구매 제안이 확정될 경우, 대상 물품의 상태는 판매 완료가 된다.</li><br>
  <li>구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 거절이 된다.</li><br>
</ul>
  </div>
</details>

#### ➡ 중고 물품 구매 제안 API 명세서 : https://documenter.getpostman.com/view/22851675/2s93zE3fex
-------------------------------------------

## Degsin pattern
![멋사 사진 1](https://github.com/likelion-backend-5th/MiniProject_Basic_ShinJaeWon/assets/95893341/2693b423-d0a2-4d84-8721-b9cb62ec5cc2)

#### Degsin pattern중 정적 메소드 패턴을 적용하여 작성하였습니다.
- 정적 메소드 패턴 (static method pattern)이 생성자를 만들어 값을 할당 받는것 보다
  더 좋은이유는 객체생성을 캡슐화 할수 있고, 가독성이 좋으며, 호출할때마다 새로운 객체를 생성할 필요가 없습니다.


## 📚 배운점
- 요구사항에 대한 예외처리를 고려해 프로그래밍 하는 습관을 길렀습니다.
- RESTful 아키텍처에 대해 배울수 있었습니다.
- 클라이언트의 요청에 대한 처리를 배울수 있었습니다.
