import{j as m,r as I,G as b,_ as k,o as a,b as n,z as s,H as D,I as v,u as o,f as i,w as d,h as x,s as _,t as w,F as C,y as E,p as P,q as V,a as B}from"./entry.88598ecb.js";import S from"./ProseA.98b68cf7.js";import{_ as M}from"./EditOnLink.vue.b18137f3.js";/* empty css                           *//* empty css                   */const N=["id","host","repo","repoid","category","categoryid","mapping","term","strict","reactionsenabled","emitmetadata","inputposition","theme","lang","loading"],O=m({__name:"Giscus",props:{id:null,host:null,repo:null,repoId:null,category:null,categoryId:null,mapping:null,term:null,theme:null,strict:null,reactionsEnabled:null,emitMetadata:null,inputPosition:null,lang:null,loading:null},setup(t){const e=I(!1);return b(()=>{e.value=!0,k(()=>import("./giscus-a53917f0.fbc3188d.js"),[],import.meta.url)}),(r,u)=>e.value?(a(),n("giscus-widget",{key:0,id:t.id,host:t.host,repo:t.repo,repoid:t.repoId,category:t.category,categoryid:t.categoryId,mapping:t.mapping,term:t.term,strict:t.strict,reactionsenabled:t.reactionsEnabled,emitmetadata:t.emitMetadata,inputposition:t.inputPosition,theme:t.theme,lang:t.lang,loading:t.loading},null,8,N)):s("",!0)}}),A=t=>(P("data-v-79daddb6"),t=t(),V(),t),F={key:0,class:"docs-page-bottom"},G={key:0,class:"edit-link"},T=A(()=>_("span",null," Edit this page on GitHub ",-1)),z={key:1},H=m({__name:"DocsPageBottom",setup(t){const{page:e}=D(),{config:r}=v();return(u,L)=>{var p,c,l;const g=E,h=S,y=M;return a(),n(C,null,[o(e)?(a(),n("div",F,[(c=(p=o(r))==null?void 0:p.github)!=null&&c.edit?(a(),n("div",G,[i(g,{name:"uil:edit"}),i(y,{page:o(e)},{default:d(({url:f})=>[i(h,{to:f},{default:d(()=>[T]),_:2},1032,["to"])]),_:1},8,["page"])])):s("",!0),(l=o(e))!=null&&l.mtime?(a(),n("span",z,[x("Updated on "),_("b",null,w(new Intl.DateTimeFormat("en-US").format(Date.parse(o(e).mtime))),1)])):s("",!0)])):s("",!0),i(o(O),{id:"comments",repo:"mineblock11/fabric-community-wiki","repo-id":"R_kgDOI8_VoA",category:"Page Comments","category-id":"DIC_kwDOI8_VoM4CWc8B",mapping:"title","reactions-enabled":"1",strict:"1","emit-metadata":"0","input-position":"top",theme:"transparent_dark",lang:"en",loading:"lazy",host:"https://giscus.app"})],64)}}}),$=B(H,[["__scopeId","data-v-79daddb6"]]);export{$ as default};