
// JavaScript Document
var $grid;
var manager, g;

 var alert = function (content)
        {
            $.ligerDialog.alert(content);
        };
        var gridManager = null;
      
        
        function showdata(){
        	//表格
            g = manager = $grid = $("#maingrid").ligerGrid({
                columns: [
              { display: '教师号', name: 'userId', minWidth: 100 },
              { display: '姓名', name: 'userName', width: 100 },
              { display: '学院', name: 'cquptCollege', minWidth: 100 },
              { display: '部门', name: 'department', width: 100 },
              { display: '性别', name: 'gender', width: 50 },
              { display: '籍贯', name: 'origin', width: 50 },
              { display: '民族', name: 'nationality', width: 50 },
              { display: '出生日期', name: 'birthday', width: 100 },
              { display: '政治面貌', name: 'politicalStatus', width: 100 },
              { display: '进入重邮时间', name: 'timeBeginCqupt', width: 100 },
              { display: '参加工作时间', name: 'timeBeginWork', width: 100 },
              { display: '第一学位', name: 'firstDegree', width: 100 },
              { display: '第一学位专业名称', name: 'firstProfessionalName', width: 150 },
              { display: '第一学位毕业学校', name: 'firstGraduateSchool', width: 100 },
              { display: '最后学位', name: 'lastDegree', width: 100 },
              { display: '最后学位专业名称', name: 'lastProfessionalName', width: 150 },
              { display: '最后学位毕业学校', name: 'lastGraduateSchool', width: 100 },
              { display: '最后学历', name: 'lastAcademic', width: 150 }
                ], dataAction: 'server', data: rows, sortName: 'courseId',
                width: '100%', height: '100%', pageSize: 100000, rownumbers:true,
                checkbox : true, pageSizeOptions : [10, 30, 50, 100, 500, 100000],
                //应用灰色表头
                cssClass: 'l-grid-gray', 
                heightDiff: -6
            });
            show();
            
           gridManager = $("#maingrid").ligerGetGridManager();

            $("#pageloading").hide();
        }

        function getInfo(){
        	
        	var stringName1 = $("#stringName1").val();
        	var stringValue1 = $("#stringValue1").val();
        	
        	var stringName2 = $("#stringName2").val();
        	var stringValue2 = $("#stringValue2").val();
        	
        	
        	
        	$.ajax({
        		type:"POST",
        		url:"searchTeacher.action",
        		datatype:"script",
        		data:{stringName1:stringName1,
	        		stringValue1:stringValue1,
	        		stringName2:stringName2,
	        		stringValue2:stringValue2
        		},
        		success:callback
        	});
        	}

        function callback(builder){
        	rows = [];
        	var callbackdate = eval("("+builder+")");
        	for(var objid in callbackdate){
        		var courseContributeInfo = callbackdate[objid];		
        		var row = { 
        			   cquptCollege: "${cquptCollege.collegeName}", 
     	     	 	   department: "${department}", 
     	     	 	   userName: "${userName}", 
					   gender: "${gender}", 
					   origin: "${origin}", 
					   nationality: "${nationality}", 
					   birthday: "${birthday}", 
					   politicalStatus: "${politicalStatus}", 
					   timeBeginCqupt: "${timeBeginCqupt}", 
					   timeBeginWork: "${timeBeginWork}", 
					   firstDegree: "${firstDegree}", 
					   firstProfessionalName: "${firstProfessionalName}", 
					   firstGraduateSchool: "${firstGraduateSchool}", 
					   lastDegree: "${lastDegree}", 
					   lastProfessionalName: "${lastProfessionalName}", 
					   lastGraduateSchool: "${lastGraduateSchool}", 
					   lastAcademic: "${lastAcademic}"
        				};
        		rows.push(row);
        	}
        	showdata();	
        }
        
     
        function show()
        {
            var jsonObj = {};
           
            jsonObj.Rows = rows;
            $grid.set({ data: jsonObj });
            
        }
   	   
   	    
        var rows = [];
