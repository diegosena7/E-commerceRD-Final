import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MinhasInformacoesComponent } from './minhas-informacoes.component';

describe('MinhasInformacoesComponent', () => {
  let component: MinhasInformacoesComponent;
  let fixture: ComponentFixture<MinhasInformacoesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MinhasInformacoesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MinhasInformacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
