#### 기존 https://github.com/likelion-backend-5th/MiniProject_Basic_ShinJaeWon 에서 고도화 작업
-----
🦁 멋쟁이사자처럼 Mini_Project 고도화

➡ 작성자 : 신재원

➡ Mail : tlswodnjs99@naver.com

> **프로젝트 개발기간: 2023.07.26 ~ 2023.08.02**

## ▶ Environment
> Language : Java 17

> IDE : Intellij

> Framework : SpringBoot (3.1.1)

➡ SpringSecurity 6.0

➡ Spring Data Jpa

> DataBase : SqlLite

> ORM : Hibernate

> Jwt

## ▶ DB ERD
![1](https://github.com/likelion-backend-5th/Project_1_ShinJaeWon/assets/95893341/47f2ca56-1cef-46ed-9450-8dac72daac30)

✔ USER - ITEM : 1 : N 관계

✔ ITEM - COMMENT : 1 : N 관계

✔ ITEM - NEGOTIATION : 1 : N 관계


----


## 👀 프로젝트 요구 사항 
### 1️⃣ 사용자 인증 기능
🛠 개발 기간 : 7.26 ~ 7.27 
<details>
<summary> 1. 사용자는 회원가입을 진행할 수 있다. </summary>
<div markdown="1">
<br>
<ul>
  <li>회원가입에 필요한 정보는 아이디와 비밀번호가 필수이다.</li><br>
  <li>부수적으로 전화번호, 이메일, 주소 정보를 기입할 수 있다.</li><br>
  <li>이에 필요한 사용자 Entity는 직접 작성하도록 한다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 2. 아이디 비밀번호를 통해 로그인을 할 수 있어야 한다.  </summary>
<div markdown="1">
<br>

  </div>
</details>
<details>
<summary> 3. 아이디 비밀번호를 통해 로그인에 성공하면, JWT가 발급된다. 이 JWT를 소유하고 있을 경우 인증이 필요한 서비스에 접근이 가능해 진다.   </summary>
<div markdown="1">
<br>
<ul>
  <li>인증이 필요한 서비스는 추후(미션 후반부) 정의한다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 4. JWT를 받은 서비스는 **사용자가 누구인지** 사용자 **Entity를 기준**으로 정확하게 판단할 수 있어야 한다. </summary>
<div markdown="1">
<br>
<ul>
</ul>
  </div>
</details>

### 2️⃣ 관계 설정 하기 (연관 관계 매핑)
🛠 개발 기간 : 7.27 ~ 7.28 
<details>
<summary> 1. 아이디와 비밀번호를 필요로 했던 테이블들은 실제 사용자 Record에 대응되도록 ERD를 수정하자. </summary>
<div markdown="1">
<br>
<ul>
  <li>ERD 수정과 함께 해당 정보를 적당히 표현할 수 있도록 Entity를 재작성하자.</li><br>
  <li>그리고 ORM의 기능을 충실히 사용할 수 있도록 어노테이션을 활용한다.</li><br>
</ul>
  </div>
</details>
<details>
<summary> 2. 다른 작성한 Entity도 변경을 진행한다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>서로 참조하고 있는 테이블 관계가 있다면, 해당 사항이 표현될 수 있도록 Entity를 재작성한다.</li><br>
</ul>
  </div>
</details>

### 3️⃣ 기능 접근 설정 하기 
🛠 개발 기간 : 7.28 ~ 7.29 

<details>
<summary> 1. 본래 “누구든지 열람할 수 있다”의 기능 목록은 사용자가 인증하지 않은 상태에서 사용할 수 있도록 한다. </summary>
<div markdown="1">
<br>
<ul>
  <li>등록된 물품 정보는 누구든지 열람할 수 있다.</li><br>
  <li>등록된 댓글은 누구든지 열람할 수 있다.</li><br>
  <li>기타 기능들</li><br>
</ul>
  </div>
</details>
<details>
<summary> 2. 작성자와 비밀번호를 포함하는 데이터는 인증된 사용자만 사용할 수 있도록 한다.  </summary>
<div markdown="1">
<br>
<ul>
  <li>이때 해당하는 기능에 포함되는 아이디 비밀번호 정보는, 1일차에 새로 작성한 사용자 Entity와의 관계로 대체한다.</li><br>
  <li>물품 정보 등록 → 물품 정보와 사용자 관계 설정</li><br>
  <li>댓글 등록 → 댓글과 사용자 관계 설정</li><br>
  <li>기타 등등</li><br>
  <li>누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.</li><br>
  <li>등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.</li><br>
  <li>등록된 물품에 대하여 구매 제안을 등록할 수 있다.</li><br>
  <li>기타 기능들</li><br>
</ul>
  </div>
</details>


## 📚 배운점
