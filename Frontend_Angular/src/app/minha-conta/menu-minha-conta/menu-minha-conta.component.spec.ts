import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuMinhaContaComponent } from './menu-minha-conta.component';

describe('MenuMinhaContaComponent', () => {
  let component: MenuMinhaContaComponent;
  let fixture: ComponentFixture<MenuMinhaContaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuMinhaContaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuMinhaContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
