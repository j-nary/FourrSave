# FourrShare
[사용자인터페이스및실습] Android Studio 포르쉐 프로젝트


### 1. 프로젝트 배경
MZ세대 친구들끼리 만나 놀면 마지막 필수코스가 되었다는 인생네컷. 이 인기에 힘입어 포토이즘, 하루필름, 포토그레이, 포토시그니처 등 인생네컷을 겨냥한 많은 종류의 셀프사진관이 생겨나고있다. 네컷사진관은 처음 생겨난 2017년부터 5년간 꾸준한 인기를 얻어오고 있는데, 2022년 5월 기준 전국에 네컷사진관의 개수가 약 1,000여개에 달했다고 하니 그 인기를 짐작해볼 수 있을 것 같다.
<img width="214" alt="image" src="https://user-images.githubusercontent.com/83453646/221410826-2ac0b67a-78eb-45a6-b201-6352eee34aef.png"><img width="225" alt="image" src="https://user-images.githubusercontent.com/83453646/221410844-cecdde53-dcd0-4fec-8d19-49ccc0050f29.png">

 위에 보이는 기사에서도 볼 수 있듯이 최근에는 MZ세대뿐만 아니라 트렌드에 민감한 사람들이 꼭 한 번쯤 찍어보고 싶은 버킷리스트가 되었다. 이처럼 네컷사진관은 트렌드에 중요한 요소로 자리잡고 있다.
 그렇다면 요새 트렌드인 네컷사진관을 이용할 때에 있어서 사용자가 불편을 느끼는 부분은 무엇일까? 이를 위해 직접 설문조사를 진행하였다. 설문조사의 기간은 일주일이었고, 주로 MZ세대를 겨냥한 프로젝트이기 때문에 MZ세대의 인기 SNS인 instagram을 통해 설문을 진행하였다. 설문 항목은 다음과 같다.
 
<img width="454" alt="image" src="https://user-images.githubusercontent.com/83453646/221410878-ef9a8387-6d33-4afc-81af-d9f95eeaf156.png">
<img width="386" alt="image" src="https://user-images.githubusercontent.com/83453646/221410884-79baa825-e06c-4349-b47d-f892b97db26e.png">

결과는 위와 같다. 결과에서도 볼 수 있듯이 네컷사진관을 이용하는 사람들이 많고 보관 및 포즈에 어려움을 느끼는 사람들 또한 많다는 점을 알 수 있다.
 따라서 이러한 부분을 보완할 앱을 기획하였다. 요즘 대세인 네컷사진관에서 사진을 찍을 때 포즈의 어려움을 느끼는 사람들을 위해 포즈 랭킹을 제공하고, 보관에 어려움을 느끼는 사람들을 위해 네컷사진 전용 갤러리를 제공하는 앱이다.
유사 프로젝트를 찾아보았다. 구글 플레이스토어에서 네컷사진과 관련된 앱을 검색해보면, 이러한 기능들을 제공하는 앱이 없음을 알 수 있다. 그나마 Instagram 해시태그를 통해 정보들을 제공받을 수 있는데, 인생네컷 포즈추천 게시글은 5000개가 넘는데 그에 비해 그곳에서 얻을 수 있는 정보는 무분별한 광고 위주의 정보임을 알 수 있다. 아래 사진에서도 알 수 있듯이 어디에서 포즈에 대한 정보를 얻어야할 지, 어떤 포즈가 가장 인기있는 포즈일 지 등에 대한 유용한 정보를 빠르게 한 눈에 찾기란 매우 어려울 것처럼 보인다.

<img width="258" alt="image" src="https://user-images.githubusercontent.com/83453646/221410898-7f6fc764-eaeb-427f-9b0f-e07543495133.png"><img width="116" alt="image" src="https://user-images.githubusercontent.com/83453646/221410904-4d47b30d-967e-4b69-b762-7e64984e269c.png">

그렇기에 이러한 부분을 보완하는 프로젝트를 계획하였다. 한 문장으로 Fourrshare는 MZ세대에게 네컷 사진을 저장하고, 공유하고, 스크랩할 수 있는 기능을 제공하는 어플리케이션이다.


### 2. 기능들에 대한 부연설명
1.	[로그인] 
•	기능 : 포르쉐의 계정을 이용한 로그인 화면으로, 로그인 및 회원가입 버튼으로 구성되어 있다.
2.	[메인 갤러리]
•	기능 : 사용자가 자신의 휴대폰 갤러리에서 가져왔던 인생네컷 사진들을 한 번에 모아볼 수 있는 메인 갤러리이다. + 버튼을 눌러 사용자의 native 갤러리에서 수동으로 사진을 업로드 할 수 있다. 또한, 하단 네비게이션에 있는 카메라 아이콘을 눌러 인생네컷의 QR코드를 찍어 바로 사진을 등록하는 기능도 제공한다.
(2-1) 갤러리 형식의 스크롤뷰를 어떻게 구현해야하는지 틀이 잡히지 않았다. Scroll View로 처음에 시도하다가 다중 이미지를 받아와서 뿌려줘야하는 기능이기에 Scroll View로 하기에는 한계점이 있다는 것을 인식했다. 다중 이미지를 받아줄 때 Adapter라는 존재가 필요하다는 것을 알고 해당 개념에 대해 공부하였고 Scroll View 대신 Recycler View 라는 존재를 인식하여 해당 개념에 대한 공부도 진행 후에 다시 처리하였다.
(2-3.2) QR코드 스캔 후, 결과로 나온 URL을 각 회사의 다운로드 링크 규격에 맞게 정규식 등을 활용하여 매칭시켰다.
(4-5, 4-6) DB에 업로드 하기 전, STORAGE에 이미지를 업로드 한 뒤 DB에는 STORAGE에 있는 이미지의 URL을 저장하는 방식을 사용하였다. 사진마다 이미지 링크, 좋아요한 사람, 좋아요 숫자, 업로드한 날짜 등 다양한 정보가 저장된다. 이 정보들의 관리를 편하게 하기 위해 DTO 파일을 정의하여 코드 내에서 다루기 쉽게 처리하였다.


3.	[확대 다이얼로그]
•	기능 : 사진을 확대해서 볼 수 있는 다이얼로그로, 랭킹 커뮤니티에 올릴 수 있는 업로드 슬라이더, 서버의 사진을 로컬 기기에 다운 받을 수 있는 다운로드 버튼, 그리고 서버에서 영구적으로 사진을 삭제할 수 있는 삭제 버튼이 있다.
(3-1) 커스텀 다이얼로그에 다중 이미지의 아이템을 띄워주는 부분에서 어려움을 겪었다. 커스텀 다이얼로그를 클래스로 따로 정의하다보니, 다이얼로그를 실제 띄워주는 Adapter로 정보를 전달하기가 힘들었다. 그래서 다이얼로그 안에서 인터페이스를 정의한 뒤 콜백함수를 호출하는 방식을 사용하였다.

4. [랭킹 커뮤니티]
•	기능 : 사용자들이 업로드한 인생네컷 사진들을 모아서 볼 수 있는 곳으로, 사용자들이 업로드 한 사진을 좋아요 순 체크박스를 체크하여 좋아요 순으로 사진을 정렬해 볼 수 도 있고 직접 맘에 드는 사진에 좋아요를 누를 수 있는 커뮤니티이다.

 (4-1) 좋아요 순으로 사진을 띄울 때, 기존 계획처럼 1,2,3등에 대해서 특별한 표시와 함께 사진의 포지션도 변경해보려고 했으나, 동점자로 3등까지 3명 초과의 사람이 나오게 될 경우 layout이 난잡해질 것을 고려해서 이 부분은 계획에서 제외하게 되었다.

 (10-1) 좋아요 버튼을 눌렀을때, 숫자와 아이콘이 변하게 하는 부분을 파이어베이스 서버에 정상적으로 반영 된 뒤로 하자 사용자 입장에서 지연시간이 생긴 것 같은 경험을 할 수 있었다. 따라서 버튼을 누르자마자, 우선적으로 좋아요 이미지와 숫자를 변화시킨 뒤, 서버에 정상적으로 반영 되었는지 한번 더 확인하는 방식으로 바꾸어 구현하였다.

5. [스크랩]
•	기능 : 사용자가 랭킹에서 좋아요를 누른 사진들을 보관해서 한 번에 볼 수 있는 화면으로, 다른 사용자가 업로드한 네컷 사진 중 자신이 다음에 찍을 때 참고할 포즈들을 모아볼 수 있다.

6. [내 정보]
•	기능 : 랭킹 커뮤니티에서 사용자가 사용하게 될 닉네임 및 프로필 사진을 설정하는 창으로 로그아웃도 가능하다.
  (6-3) 기존에는 닉네임 기능만 제공하여, 랭킹에서 유저의 닉네임만 띄우는 형식으로 프로젝트를 진행했었다. 기존 계획에 맞춰 어느정도 프로젝트가 완성되어가는 무렵에, 부가 기능을 더 고안하다가 유저 피드를 추가하게 되었고, 더 개성있는 유저들의 피드를 구성하기 위해 프로필 사진 설정 기능을 추가적으로 제공하게 되었다. 

7. [바텀 네비게이션]
•	기능 : 메인 갤러리, 랭킹, QR 카메라, 스크랩, 내 정보로 이동 해준다.
  (7-1) 당시에 앱의 전체적 구성을 먼저 갖추기 위해 바텀 네비게이션을 일찍 만들어 두는 것이 좋겠다고 판단되어서 계획이 앞당겨졌다. 하지만 Fragment 학습 및 사용에 어려움을 겪으면서 Activity를 이용한 구현으로 우회 하게 되었다. Activity로도 Fragment만큼 빠른 반응성을 가질 수 있도록 Activity 전환 애니메이션을 일절 제거하고, 백 스택에 쌓이지 않게 finish를 써서 Fragment 성능 및 기능과 유사하도록 구현을 했다.
  (7-2) 기존 디자인으로 하늘색에 회색 아이콘으로 바텀 네비게이션을 구성했었는데, 주변 사람들에게 사용자 입장으로서의 의견을 받아본 결과 바텀 네비게이션의 크기가 너무 큰 것 같고 테마가 밋밋하다는 의견이 있었다. 이에 따라 앱의 전체적인 테마를 수정하게 되었고, 그에 맞춰 디자인 및 크기를 변경하게 되었다.

9. [유저 피드]
•	기능 : 랭킹에서 다른 유저의 프로필 사진 및 닉네임을 터치했을 때, 해당 유저가 업로드 해 둔 사진을 모아서 볼 수 있도록 피드를 구성했다. 피드 내에서도 네컷 사진에 대해 좋아요를 누를 수 있도록 기능을 구현해 두었다. 백버튼을 누르면 다시 랭킹으로 돌아간다.

 (8-1) 특정 유저가 올린 사진에 대해 맘에 드는 사진이 많아서 해당 유저가 올린 인생네컷 사진들을 모아 보고싶은 경우, 랭킹에서 하나하나 찾아보는 방법은 불편할 것이라는 의견이 생겼다. 이에 따라 그 사용자의 사진을 모아볼 수 있는 피드를 따로 만들어보자는 결론이 나게 되어, 부가기능으로 추가하게 되었다.


### 3. 최종 작업 분해표
<img width="440" alt="image" src="https://user-images.githubusercontent.com/83453646/221411000-e816e141-f721-484d-96b5-518000e7f1ef.png"><img width="440" alt="image" src="https://user-images.githubusercontent.com/83453646/221411005-7d4e1142-00d5-459c-a311-1e8da1db14f0.png"><img width="440" alt="image" src="https://user-images.githubusercontent.com/83453646/221411014-24f73757-58df-4b35-a2aa-3c6b061b5ed1.png">

### 4. 사용된 FireBase 기능
- Authentication (이메일/비밀번호 기반)
- Storage 
- FireStore Database
