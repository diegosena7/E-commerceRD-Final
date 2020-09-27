import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeusEnderecosComponent } from './meus-enderecos.component';

describe('MeusEnderecosComponent', () => {
  let component: MeusEnderecosComponent;
  let fixture: ComponentFixture<MeusEnderecosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeusEnderecosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeusEnderecosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
