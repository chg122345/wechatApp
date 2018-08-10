#建表语句
#create database gxf_db;
# use gxf_db;
#*****************************************************************#

create table if not exists gxf_admin(
id int unique auto_increment COMMENT '主键自增',
name varchar(50) not null COMMENT '会员昵称',
password char(32) not null COMMENT '密码32位加密',
phone varchar(30) not null COMMENT '电话号码',
email varchar(50) not null COMMENT '邮箱',
primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息表';

create table if not exists gxf_user(
  id int unsigned auto_increment COMMENT '主键自增',
  name nvarchar(45) default null COMMENT '昵称',
  password char(32) default null COMMENT '密码32位MD5加密',
  sex int(2) default 0 COMMENT '性别',
  address nvarchar(55) default null COMMENT '地址',
  phone nvarchar(20) default null COMMENT '电话',
  status int(2)  default 0 COMMENT '认证信息 0没认证 1已认证',
  logined datetime default CURRENT_TIMESTAMP COMMENT '最近登录的时间',
  loginip nvarchar(100) default null COMMENT '最近登录的IP地址',
  created datetime default CURRENT_TIMESTAMP COMMENT '注册时间',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表，用于保存用户信息。';


create table if not exists gxf_goods(
  id int unsigned auto_increment COMMENT '主键自增',
  name nvarchar(125) default null COMMENT '名称',
  price double default 0 COMMENT '单价',
  type int(3) default 0 COMMENT '分类 4->其他类 1->鲜果类 2->蔬菜类 3->肉类 ',
  abstracts nvarchar(255) default null COMMENT '描述',
  img nvarchar(200) default null COMMENT '插图',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表，用于保存商品信息。';


create table if not exists gxf_webinfo(
  id int unsigned auto_increment COMMENT '主键自增',
  title nvarchar(125) default null COMMENT '名称',
  notice nvarchar(255) default null COMMENT '公告 | 分开多条公告',
  copyright nvarchar(255) default null COMMENT '版权信息',
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点信息表。';


create table if not exists gxf_order(
id int unsigned auto_increment COMMENT '主键自增',
orderId varchar(50) not null COMMENT '订单随机编号',
totalNumber int default 0 COMMENT '购物数量总计',
totalMoney double default 0.0 COMMENT '购物金额总计',
status int default 0 COMMENT '订单状态 0未付款 1已付款 2未发货 3已发货 4 交易完成',
user_id char(32) not null COMMENT '对应的用户的id',
user_name varchar(50) not null COMMENT '对应的用户',
user_phone varchar(50) not null COMMENT '对应的用户电话',
user_address varchar(255) not null COMMENT '对应的用户地址',
orderTime TIMESTAMP default CURRENT_TIMESTAMP COMMENT '下单时间',
primary key (id),
key (orderId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品订单表，用于保存商品订单信息。';


create table if not exists gxf_orderitem(
id int unsigned auto_increment COMMENT '主键自增',
number int default 0 COMMENT '购物数量小计',
money double default 0.0 COMMENT '购物金额小计',
goods_id int not null COMMENT '对应的商品id',
order_id varchar(50) not null COMMENT '所属的订单id',
primary key (id),
key (goods_id),
foreign key (order_id) references gxf_order(orderId) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品订单项表，用于保存商品订单项信息。';