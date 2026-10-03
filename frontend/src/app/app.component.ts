import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
@Component({
 selector:'app-root', standalone:true, imports:[CommonModule,FormsModule],
 template:`
 <div class="shell">
  <aside><h2>Enterprise AI</h2><nav>Dashboard<br><b>AI Chat</b><br>Documents<br>Knowledge<br>Agents<br>Audit Logs<br>Analytics<br>Admin</nav></aside>
  <main>
   <header><div><small>ENTERPRISE KNOWLEDGE PLATFORM</small><h1>Ask your organization</h1></div><span class="pill">Grounded RAG</span></header>
   <section class="stats"><article><b>RAG</b><span>Grounded answers</span></article><article><b>RBAC</b><span>Protected APIs</span></article><article><b>pgvector</b><span>Semantic retrieval</span></article></section>
   <section class="card">
    <h3>AI Assistant</h3><p>Ask questions across approved enterprise documents. Answers include source citations.</p>
    <textarea [(ngModel)]="question" placeholder="What are our incident response procedures?"></textarea>
    <button (click)="ask()" [disabled]="loading">{{loading?'Searching…':'Ask Enterprise AI'}}</button>
    <div class="answer" *ngIf="answer"><h3>Answer</h3><p>{{answer}}</p><h4>Sources</h4><div class="source" *ngFor="let c of citations">[{{c.page}}] {{c.title}} · relevance {{c.score}}</div></div>
   </section>
  </main>
 </div>`,
 styles:[]
})
export class AppComponent {
 question=''; answer=''; citations:any[]=[]; loading=false;
 constructor(private http:HttpClient){}
 ask(){ if(!this.question.trim())return; this.loading=true;
  this.http.post<any>('/api/rag/query',{question:this.question}).subscribe({
   next:r=>{this.answer=r.answer;this.citations=r.citations||[];this.loading=false;},
   error:()=>{this.answer='Backend connection required. Start the Spring Boot API to run grounded retrieval.';this.loading=false;}
  });
 }
}
