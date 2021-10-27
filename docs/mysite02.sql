desc board;
desc user;
desc guestbook;
show tables;

select * from guestbook;
select * from user;	
update board set hit=hit+1 where no=1;

select no, name from user where email=1 and password=123;

desc board;
select b.no, b.title, b.contents, b.hit, b.reg_date, b.group_no, b.order_no, b.depth, u.name
  from board b, user u
 where b.user_no = u.no
  -- and b.title like concat('%','삭제','%')
  -- and b.title like "%제목%"
 order by b.group_no desc;
 limit 2,2;
 111111
 select no, title, contents from board where no=40 and user_no=40;
 desc user;
select count(*) from board;
update board
   set title='제목 22', contents='내용 22'
 where no=2;
 
select no, title, contents, hit, reg_date, group_no, order_no, depth, user_no 
  from board
order by group_no desc, order_no desc, depth asc;

  
insert into board values (null, "제목 1", "내용 1", 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), (SELECT IFNULL(MAX(order_no) + 1, 1) FROM board b), (SELECT IFNULL(MAX(depth) + 1, 1) FROM board b), 2);
insert into board values (null, "제목 2", "내용 2", 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, 2);
insert into board values (null, "제목 3", "내용 3", 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, 2);
insert into board values (null, "제목 4", "내용 4", 0,  now(), (SELECT IFNULL(MAX(group_no) + 1, 1) FROM board b), 0, 0, 1);
insert into board values (null, "댓글 3", "댓글 내용 3", 0,  now(), 0, 0, 0, 1);

delete from board where title = '삭제된 게시물 및 댓글입니다.';

select * from user;
select group_no, order_no, depth
  from board
 where no = 9;


desc board;
