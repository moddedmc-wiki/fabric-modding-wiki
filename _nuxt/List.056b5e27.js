import{j as r,B as l,k as u,C as n,y as p,D as m,a as _}from"./entry.88598ecb.js";import f from"./ContentSlot.03c71ebd.js";/* empty css                 */const d={primary:"heroicons-outline:check",info:"heroicons-outline:information-circle",success:"heroicons-outline:check-circle",warning:"heroicons-outline:exclamation",danger:"heroicons-outline:exclamation-circle"},y=r({props:{icon:{type:String,default:null},type:{type:String,default:"primary",validator:e=>["primary","info","success","warning","danger"].includes(e)}},setup(e){const t=l(),{flatUnwrap:a,unwrap:s}=m(),c=u(()=>e.icon||d[e.type]);return()=>{const i=a((t.default&&t.default())??[],["ul"]).map(o=>s(o,["li"]));return n("ul",i.map(o=>n("li",[n("span",{class:`list-icon ${e.type}`},n(p,{name:c.value,class:"icon"})),n("span",n(f,{use:()=>o}))])))}}}),w=_(y,[["__scopeId","data-v-6109395b"]]);export{w as default};