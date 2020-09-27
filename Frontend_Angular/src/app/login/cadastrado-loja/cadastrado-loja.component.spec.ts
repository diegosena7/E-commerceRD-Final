import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastradoLojaComponent } from './cadastrado-loja.component';

describe('CadastradoLojaComponent', () => {
  let component: CadastradoLojaComponent;
  let fixture: ComponentFixture<CadastradoLojaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastradoLojaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastradoLojaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
