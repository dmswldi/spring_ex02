//console.log("reply module...");

var replyService =(function() {

	function add(reply, callback, error) {
		//console.log('add method');
		console.log(reply);
		
		$.ajax({
			type: 'POST',
			url: appRoot + '/replies/new',
			data: JSON.stringify(reply),/* js obj를 json으로 */
			contentType: 'application/json; charset=utf-8',
			success: function(result, status, xhr){/* ResponseEntity의 body에 담아준 data */
				if(callback){/* callback !== undefined */			
					callback(result);					
					/*
					console.log('data: ' + result);// ResponseEntity의 body 출력
					console.log('xhr: ' + xhr.responseText);// ResponseEntity의 body 출력
					*/
				}
			},
			error: function(xhr, status, er){
				if(error){/* error !== undefined */
					error(er);
				}
			}
		});
	}
	
	
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;// undefined면 1
		
		$.getJSON(
			appRoot + '/replies/pages/' + bno + '/' + page,/* url 실행하고 리턴된 responseEntity의 body가 data */
			function(data, status, xhr){/* responseEntity에서 body에 담아 리턴한 list == data */
				if(callback) {
					callback(data);
					/*
					console.log(data);
					console.log(status);	
					console.log(xhr);	
					console.log(xhr.responseText);	
					*/
				}
			}
		).fail(function(xhr, status, err){
			if(error) error();
		});
	}
	
	function remove(rno, callback, error){
		$.ajax({
			type: 'DELETE',
			url: appRoot + '/replies/' + rno,
			success: function(result, status, xhr){
				if(callback) callback(result);
			},
			error: function(xhr, status, er){
				if(error) error(er);
			}
		});
	}
	
	function update(reply, callback, error){
		$.ajax({
			type: 'PUT',
			url: appRoot + '/replies/' + reply.rno,
			data: JSON.stringify(reply), /* js obj를 json 객체로 변환 */
			contentType: 'application/json; charset=utf-8',
			success: function(result, status, xhr){
				if(callback) callback(result);
			},
			error: function(xhr, status, er){
				if(error) error(er);
			}
		});
	}
	
	function get(rno, callback, error) {
		$.get(appRoot + '/replies/' + rno, function(data) { /* getJSON 안 해도 알아서 dataType 추론 */
			if(callback) callback(data);
		}).fail(function(){
			if(error) error();
		});
	}
	
	return {
		name:"AAAA",
		add: add, /* 함수 실행*/
		getList: getList,
		remove: remove,
		update: update,
		get: get
	};
})();



//console.log(replyService.name);
//console.log(replyService.add);/* 함수 자체 */
//replyService.add("my repl");

/*
replyService.add({bno:205, reply: 'new reply~!', replyer: 'mee'},
	function(result){
		console.log('result: ' + result);// ResponseEntity의 body 출력
	}, function(err){
		console.log(err);
	});
*/
/*
replyService.getList({bno:205, page:1}, function(data){
		console.log('data: ' + data);
	}, function(){
		console.log('error');
	});
*/
/*
replyService.remove(61, function(data){
		console.log(data);
	}, function(err){
		console.log(err);
	});
*/
/*
replyService.update({rno: 62, reply: 'modify using ajax'}, function(data){
		console.log(data);// success 출력
	}, function(err){
		console.log(err);
	});
*/
/*
replyService.get(50, function(data){
	console.log(data);
}, function(err){
	console.log(err);
});


*/