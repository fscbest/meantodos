var express = require('express');
var router = express.Router();
var mongojs = require('mongojs');
var db = mongojs('mongodb://roman:roman@ds041566.mlab.com:41566/meanstodosapp',['todos']);
var isOnWork = true;

//Get all todos
router.get('/todos', function(req, res, next){
	console.log("todos router todos path. isOnWork = ");
	if(!isOnWork){
		db.todos.find(function(err, todos){
			if(err){
				res.send(err);
			} else {
				res.json(todos);
			}
		});
	} else {
		res.json([{"_id":"57e8f042dcba0f568ee386f3","text":"Go out to dinner.","isCompleted":false},{"_id":"57e8f08bdcba0f568ee386ff","text":"Goo food shopping","isCompleted":false},{"_id":"57e8f0a0dcba0f568ee3870f","text":"Meeting at isOnWork","isCompleted":false}]);
	}

});


//Get single todos
router.get('/todo/:id', function(req, res, next){
	db.todos.findOne({
		_id: mongojs.ObjectId(req.params.id)
	}, function(err, todo){
		if(err){
			res.send(err);
		} else {
			res.json(todo);
		}
	});
});

//Save todo
router.post('/todo', function(req, res, next){
	if(!isOnWork) {
		var todo = req.body;
		if (!todo.text || !(todo.isCompleted + '')) {
			res.status(400);
			res.json({
				"error": "Invalid Data"
			});
		} else {
			db.todos.save(todo, function (err, result) {
				if (err) {
					res.send(err);
				} else {
					res.json(result);
				}
			});
		}
	} else {
		res.json({"_id":"57e8f042dcba0f56wee386f3","text":"New one.","isCompleted":false});
	}
});

//Update todo
router.put('/todo/:id', function(req, rest, next){
	var todo = req.body;
	var updObj = {};

	if(todo.isCompleted){
		updObj.isCompleted = todo.isCompleted;
	}

	if(todo.text){
		updObj.text = todo.text;
	}

	if(!updObj){
		res.status(400);
		res.json({
			"error":"Invalid Data"
	});
	} else {
		db.todos.update({
			_id: mongojs.ObjectId(req.params.id)
		}, updObj, {}, function(err, result){
			if(err){
				res.send(err);
			} else {
				res.json(result);
			}
		});
	}
});

//Delete todos
router.delete('/todo/:id', function(req, res, next){
	db.todos.remove({
		_id:mongojs.ObjectId(req.params.id)
	}, '', function(err, result){
		if(err){
			res.send(err);
		} else {
			res.json(result);
		}
	});
});

module.exports = router;
