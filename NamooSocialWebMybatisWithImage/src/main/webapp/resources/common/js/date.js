// $.getDate. 날짜를 PHP 스타일로 변경. 모든걸 지원하는건 아니고, 자주 쓰는 것들만. - 2011.03.09 bitofsky@naver.com
// 펌/수정/재배포는 원작자 표기 유지를 전제로 가능 합니다.(copyright)
(function($){
  $.extend({
    getDate: function( str, time ){
      if( time === undefined ) time = $.now();
      if( time.length == 14 && !isNaN(time) ){
        var time = String(time);
        var sp   = [time.substr(0,4),time.substr(4,2),time.substr(6,2),time.substr(8,2),time.substr(10,2),time.substr(12,2)];
        var date = new Date(sp[0]||null,(sp[1]-1)||null,sp[2]||null,sp[3]||null,sp[4]||null,sp[5]||null,sp[6]||null);
      }
      else if( isNaN(Date.parse(time)) && isNaN(new Date(time)) ){
        var sp   = String(time).split(/\D/);
        var date = new Date(sp[0]||null,(sp[1]-1)||null,sp[2]||null,sp[3]||null,sp[4]||null,sp[5]||null,sp[6]||null);
      }
      else
        var date = new Date( time );

      return str.replace(/(Y|y|n|m|d|j|a|A|g|G|h|H|i|s|DD|ll|D|l)/g, function( word ){
        switch( word ){
          case "Y": return date.getFullYear(); break;
          case "y": return String(date.getFullYear()).substring(2,4); break;
          case "n": return Number(date.getMonth())+1; break;
          case "m": return (date.getMonth() < 9 ? "0" : "" )+(date.getMonth()+1); break;
          case "d": return (date.getDate() < 10 ? "0" : "" )+date.getDate(); break;
          case "j": return date.getDate(); break;
          case "a": return (date.getHours() < 12 ? "am":"pm"); break;
          case "A": return (date.getHours() < 12 ? "AM":"PM"); break;
          case "g": return (date.getHours() < 12 ? date.getHours() : Number(date.getHours())-12); break;
          case "G": return date.getHours(); break;
          case "h": var tmp = (date.getHours() < 12 ? date.getHours() : Number(date.getHours())-12); return (tmp < 10 ? "0":"" )+tmp; break;
          case "H": return (date.getHours() < 10 ? "0" : "" )+date.getHours(); break;
          case "i": return (date.getMinutes() < 10 ? "0" : "" )+date.getMinutes(); break;
          case "s": return (date.getSeconds() < 10 ? "0" : "" )+date.getSeconds(); break;
          case "D": return ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"][date.getDay()]; break;
          case "l": return ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][date.getDay()]; break;
          case "DD": return ["일","월","화","수","목","금","토"][date.getDay()]; break;
          case "ll": return ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"][date.getDay()]; break;
          default : return word;
        }
      });
    },
    // 두 날짜의 차이를 구하는 함수
    getDateDiff: function(date){
    	//
    	var now = new Date(); // 현재 날짜
    	var diffDate = new Date(date); // 비교할 과거의 날짜
    	var diff = now - diffDate; // 로그아웃한 시간과 현재 시간의 차를 밀리세컨드로 표시
    	var diffSeconds = parseInt(diff / 1000); // 차를 초 단위로 표시
    	var diffMinutes = parseInt(diffSeconds / 60); // 차를 분 단위로 표시
    	var diffHour = parseInt(diffMinutes / 60); // 차를 시간 단위로 표시
    	var diffDays = parseInt(diffHour / 24); // 차를 일 단위로 표시
    	var diffWeaks = parseInt(diffDays / 7); // 차를 주 단위로 표시
    	var diffMonths = parseInt(diffWeaks / 4); // 차를 월 단위로 표시
    	var diffYears = parseInt(diffMonths / 12); // 차를 년 단위로 표시
    	if(date == null) {
    		//
    		return '방금 로그아웃';
    		//
    	} else if(diff < 0){
    		//
    		return '날짜 오류';
    		//
    	} else if(diffSeconds < 1) {
    		//
    		return '방금 로그아웃';
    		//
    	} else if(diffMinutes < 1){
    		//
    		return diffSeconds + '초 동안 오프라인';
    		//
    	} else if(diffHour < 1){
    		//
    		return diffMinutes + '분 동안 오프라인';
    		//
    	} else if(diffDays < 1){
    		//
    		return diffHour + '시간 동안 오프라인';
    		//
    	} else if(diffWeaks < 1){
    		//
    		return diffDays + '일 동안 오프라인';
    		//
    	} else if(diffMonths < 1){
    		//
    		return diffWeaks + '주 동안 오프라인';
    		//
    	} else if(diffYears < 1){
    		//
    		return diffMonths + '달 동안 오프라인';
    		//
    	} else{
    		//
    		return diffYears + '년 동안 오프라인';
    	}
    }
  });
})(jQuery);