import{j as _,r as l,S as b,o as a,b as s,F as v,i as g,s as u,z as x,v as y,m as I,t as k,p as S,q as T,$,a as C}from"./entry.88598ecb.js";/* empty css                       */const q=t=>(S("data-v-11acbdc3"),t=t(),T(),t),w={class:"tabs-header"},B=["onClick"],N=q(()=>u("span",{class:"tab"},null,-1)),U=[N],z=_({__name:"TabsHeader",props:{tabs:{type:Array,required:!0},activeTabIndex:{type:Number,required:!0}},emits:["update:activeTabIndex"],setup(t,{emit:p}){const h=t,n=l(),r=l(),o=e=>{e&&(r.value.style.insetInlineStart=`${e.offsetLeft}px`,r.value.style.width=`${e.clientWidth}px`)},f=(e,c)=>{p("update:activeTabIndex",c),$(()=>o(e.target))};return b(n,e=>{e&&setTimeout(()=>{o(n.value.children[h.activeTabIndex])},50)},{immediate:!0}),(e,c)=>(a(),s("div",w,[t.tabs?(a(),s("div",{key:0,ref_key:"tabsRef",ref:n,class:"tabs"},[(a(!0),s(v,null,g(t.tabs,({label:d},i)=>(a(),s("button",{key:`${i}${d}`,class:I([t.activeTabIndex===i?"active":"not-active"]),onClick:m=>f(m,i)},k(d),11,B))),128)),u("span",{ref_key:"highlightUnderline",ref:r,class:"highlight-underline"},U,512)],512)):x("",!0),y(e.$slots,"footer",{},void 0,!0)]))}}),L=C(z,[["__scopeId","data-v-11acbdc3"]]);export{L as default};