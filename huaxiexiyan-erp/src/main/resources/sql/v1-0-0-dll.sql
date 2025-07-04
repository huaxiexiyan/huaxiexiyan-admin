-- 菜单表
DROP TABLE IF EXISTS "public"."cat_menu";
CREATE TABLE "public"."cat_menu"
(
    "id"                int8         NOT NULL,
    "parent_id"         int8         NOT NULL,
    "path"              varchar(255) NOT NULL,
    "name"              varchar(255),
    "component"         varchar(255) NOT NULL,
    "redirect"          varchar(255),
    "title"             varchar(255) NOT NULL,
    "icon"              varchar(255),
    "expanded"          int2,
    "order_no"          int4,
    "hidden"            int2,
    "hidden_breadcrumb" int2,
    "single"            int2,
    "frame_src"         varchar(255),
    "frame_blank"       int2,
    "keep_alive"        int2,

    "created_by"        int8         NOT NULL,
    "created_time"      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "last_modify_by"    int8,
    "last_modify_time"  timestamp,
    "deleted_by"        int8,
    "deleted_time"      timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."cat_menu" IS '资源实体表';
COMMENT ON COLUMN "public"."cat_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."cat_menu"."parent_id" IS '上级菜单，最多3层';
COMMENT ON COLUMN "public"."cat_menu"."path" IS '是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。';
COMMENT ON COLUMN "public"."cat_menu"."name" IS '影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，请保证该路由的name与对应页面（SFC)的name保持一致。';
COMMENT ON COLUMN "public"."cat_menu"."component" IS '渲染该路由时使用的页面组件路径位置';
COMMENT ON COLUMN "public"."cat_menu"."redirect" IS '重定向的路径';
COMMENT ON COLUMN "public"."cat_menu"."title" IS '该路由在菜单上展示的标题';
COMMENT ON COLUMN "public"."cat_menu"."icon" IS '该路由在菜单上展示的图标';
COMMENT ON COLUMN "public"."cat_menu"."expanded" IS '决定该路由在菜单上是否默认展开';
COMMENT ON COLUMN "public"."cat_menu"."order_no" IS '该路由在菜单上展示先后顺序，数字越小越靠前，默认为零';
COMMENT ON COLUMN "public"."cat_menu"."hidden" IS '决定该路由是否在菜单上进行展示';
COMMENT ON COLUMN "public"."cat_menu"."hidden_breadcrumb" IS '如果启用了面包屑，决定该路由是否在面包屑上进行展示';
COMMENT ON COLUMN "public"."cat_menu"."single" IS '如果是多级菜单且只存在一个节点，想在菜单上只展示一级节点，可以使用该配置。请注意该配置需配置在父节点';
COMMENT ON COLUMN "public"."cat_menu"."frame_src" IS '内嵌 iframe 的地址';
COMMENT ON COLUMN "public"."cat_menu"."frame_blank" IS '内嵌 iframe 的地址是否以新窗口打开';
COMMENT ON COLUMN "public"."cat_menu"."keep_alive" IS '可决定路由是否开启keep-alive，默认开启';

COMMENT ON COLUMN "public"."cat_user"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."cat_user"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."cat_user"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."cat_user"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."cat_user"."deleted_by" IS '删除人';
COMMENT ON COLUMN "public"."cat_user"."deleted_time" IS '删除时间';

-- 创建资源实体表
DROP TABLE IF EXISTS "public"."cat_user";
CREATE TABLE "public"."cat_user"
(
    "id"               int8         NOT NULL,
    "username"         varchar(255) NOT NULL,
    "password"         varchar(255) NOT NULL,
    "nickname"         varchar(255) NOT NULL,

    "created_by"       int8         NOT NULL,
    "created_time"     timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "last_modify_by"   int8,
    "last_modify_time" timestamp,
    "deleted_by"       int8,
    "deleted_time"     timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."cat_user" IS '用户表';
COMMENT ON COLUMN "public"."cat_user"."id" IS '主键';
COMMENT ON COLUMN "public"."cat_user"."username" IS '登录名';
COMMENT ON COLUMN "public"."cat_user"."password" IS '密码';
COMMENT ON COLUMN "public"."cat_user"."nickname" IS '昵称';

COMMENT ON COLUMN "public"."cat_user"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."cat_user"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."cat_user"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."cat_user"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."cat_user"."deleted_by" IS '删除人';
COMMENT ON COLUMN "public"."cat_user"."deleted_time" IS '删除时间';

-- 创建角色实体表
DROP TABLE IF EXISTS "public"."cat_role";
CREATE TABLE "public"."cat_role"
(
    "id"               int8        NOT NULL,
    "name"             varchar(50) NOT NULL,

    "created_by"       int8        NOT NULL,
    "created_time"     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "last_modify_by"   int8,
    "last_modify_time" timestamp,
    "deleted_by"       int8,
    "deleted_time"     timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."cat_role" IS '角色实体表';
COMMENT ON COLUMN "public"."cat_role"."id" IS '主键';
COMMENT ON COLUMN "public"."cat_role"."name" IS '角色名称';

COMMENT ON COLUMN "public"."cat_role"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."cat_role"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."cat_role"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."cat_role"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."cat_role"."deleted_by" IS '删除人';
COMMENT ON COLUMN "public"."cat_role"."deleted_time" IS '删除时间';

-- 创建用户角色关联表
DROP TABLE IF EXISTS "public"."cat_user_role";
CREATE TABLE "public"."cat_user_role"
(
    "id"               int8      NOT NULL,
    "user_id"          int8      NOT NULL,
    "role_id"          int8      NOT NULL,

    "created_by"       int8      NOT NULL,
    "created_time"     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "last_modify_by"   int8,
    "last_modify_time" timestamp,
    "deleted_by"       int8,
    "deleted_time"     timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."cat_user_role" IS '用户角色关联表';
COMMENT ON COLUMN "public"."cat_user_role"."id" IS '主键';
COMMENT ON COLUMN "public"."cat_user_role"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."cat_user_role"."role_id" IS '角色id';

COMMENT ON COLUMN "public"."cat_user_role"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."cat_user_role"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."cat_user_role"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."cat_user_role"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."cat_user_role"."deleted_by" IS '删除人';
COMMENT ON COLUMN "public"."cat_user_role"."deleted_time" IS '删除时间';

-- 创建角色菜单关联表
DROP TABLE IF EXISTS "public"."cat_role_menu";
CREATE TABLE "public"."cat_role_menu"
(
    "id"               int8      NOT NULL,
    "role_id"          int8      NOT NULL,
    "menu_id"          int8      NOT NULL,

    "created_by"       int8      NOT NULL,
    "created_time"     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "last_modify_by"   int8,
    "last_modify_time" timestamp,
    "deleted_by"       int8,
    "deleted_time"     timestamp,
    PRIMARY KEY ("id")
);
COMMENT ON TABLE "public"."cat_role_menu" IS '角色菜单关联表';
COMMENT ON COLUMN "public"."cat_role_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."cat_role_menu"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."cat_role_menu"."menu_id" IS '菜单id';

COMMENT ON COLUMN "public"."cat_role_menu"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."cat_role_menu"."created_time" IS '创建时间';
COMMENT ON COLUMN "public"."cat_role_menu"."last_modify_by" IS '最后修改人';
COMMENT ON COLUMN "public"."cat_role_menu"."last_modify_time" IS '最后修改时间';
COMMENT ON COLUMN "public"."cat_role_menu"."deleted_by" IS '删除人';
COMMENT ON COLUMN "public"."cat_role_menu"."deleted_time" IS '删除时间';


