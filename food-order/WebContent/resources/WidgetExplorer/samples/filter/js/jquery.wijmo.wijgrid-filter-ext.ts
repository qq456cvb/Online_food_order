/// <reference path="../../../../../Wijmo/Base/jquery.wijmo.widget.ts"/>
/// <reference path="../../../../../Wijmo/wijfilter/jquery.wijmo.wijfilter.ts"/>
/// <reference path="../../../../../Wijmo/wijgrid/Grid.ts/Grid/wijgrid.ts"/>

module wijmo.wijgridfilter {
	"use strict";

	var $ = jQuery;

	var displayNames = {
		nofilter: "Choose One", contains: "Contains", notcontain: "Does Not Contain", beginswith: "Begins With",
		endswith: "Ends With", equals: "Equals", notequal: "Does Not Equal", greater: "Greater", less: "Less",
		greaterorequal: "Greater Or Equal", lessorequal: "Less Or Equal", isempty: "Is Empty", notisempty: "Does Not Empty",
		isnull: "Is Null", notisnull: "Does Not Null"
	};


	export class wijgridfilter extends wijmo.grid.wijgrid {
		options: any;
		_dialog: JQuery;

		_create() {
			this._super.apply(this, arguments);

			this.element.bind("wijgridfilterloaded", $.proxy(this._onLoaded, this));
		}

		private _onLoaded(e, args) {
			var self = this;

			$.each(this.columns(), function (i, colObj) {
				if (colObj instanceof $.wijmo.c1field) {
					colObj._hasFilter = self._hasFilter(colObj.options);

					colObj._dialogButton =
						$("<a href=\"#\"/>")
							.addClass("filterDialog-dialogButton")
							.addClass(colObj._hasFilter ? "ui-state-active" : "")
							.button({
								icons: {
									primary: "ui-icon-search"
								}
							})
							.click($.proxy(function (e) { self._showDialog(colObj); return false; }, self))
							.mouseout(function () {
								if (colObj._hasFilter) {
									colObj._dialogButton.addClass("ui-state-active");
								}
							})
							.appendTo(this.element.find(".wijmo-wijgrid-headertext"));
				}
			});
		}

		private _closeDialog(colObj, flag = false) {
			try {
				if (colObj._dialogButton) {
					colObj._dialogButton.removeClass("ui-state-focus"); // fix focus state issue
				}

				if (!flag && this._dialog) {
					this._dialog.dialog("close");
				}

				if (this._dialog) {
					this._dialog.remove();
				}
			}
			finally {
				this._dialog = null;
			}
		}

		private _data(column: wijmo.grid.IColumn): any[]{
			var result = [],
				data = this.data();


			if (data) {
				$.each(data, (idx: number, dataItem) => {
					var obj = {},
						value = dataItem[column.dataKey];

					if ($.isFunction(value)) {
						value = value();
					}

					value = (value === null || value === undefined)
						? value
						: this.toStr(column, value); // format


					obj[column.dataKey] = value;

					result.push(obj);
				});
			}

			return result;
		}

		private _showDialog(colObj) {
			var self = this,
				col: wijmo.grid.IColumn = colObj.options;

			if (this._dialog) {
				this._closeDialog(colObj);
			}

			if (!this._dialog) {
				var data = this._data(col),
					filterValue = this._convertFilterValue(col),
					filterOperator = this._convertFilterOperator(col),
					self = this;

				this._dialog = $("<div></div")
					.addClass("ui-helper-hidden-accessible filterDialog-dialogContainer ui-widget-content ui-corner-all ui-helper-clearfix")
					.appendTo(document.body)
					.wijfilter({
						data: data,
						dataKey: col.dataKey,
						title: col.headerText || col.dataKey,
						enableSortButtons: this.options.allowSorting && col.allowSort,
						sortDirection: col.sortDirection || "none",
						availableOperators: this._getAvailableFilterOperators(col.dataType || "string"),
						filterValue: filterValue,
						filterOperator: filterOperator,
						showHeader: false,

						close: function (e, args) {
							var parse = function (column: wijmo.grid.IColumn, value: any): any {
								return (value === undefined || value === null)
									? value
									: self.parse(col, value);
							};

							self._closeDialog(colObj);
									
							if (args && !$.isEmptyObject(args)) {
								col.sortDirection = args.sortDirection;
								
								// parse filter values
								$.each(args.filterValue, function (i, val1) {
									if ($.isArray(val1)) {
										$.each(val1, function (j, val2) {
											val1[j] = parse(col, val2);
										});
									} else {
										args.filterValue[i] = parse(col, val1);
									}
								});

								col.filterValue = args.filterValue;
								col.filterOperator = args.filterOperator;

								self.options.pageIndex = 0;

								self.ensureControl(true);
							}
						}
					})
					.removeClass("ui-helper-hidden-accessible")
					.dialog({
						resizable: false,
						modal: true,
						closeText: "",
						title: col.headerText || col.dataKey,
						width: "auto",
						height: "auto",
						position: {
							of: colObj._dialogButton,
							my: "left top",
							at: "left bottom",
							collision: "flip none"
						},
						close: function () {
							self._closeDialog(colObj, true);
						}
					});
			}
		}

		private _getAvailableFilterOperators(dataType: string) {
			var self = this,
				result = $.map(this.getFilterOperatorsByDataType(dataType), function (fop) {
					return {
						name: fop.name,
						displayName: fop.displayName || displayNames[fop.name.toLowerCase()]
					};
				});

			return result;
		}

		private _hasFilter(column: wijmo.grid.IColumn): bool {
			var fv = column.filterOperator,
				i, len, foo,
				flag = false;

			if (!$.isArray(fv)) {
				fv = [fv];
			}

			for (i = 0, len = fv.length; (i < len) && !flag; i++) {
				foo = fv[i];
				flag = (foo && (foo = (foo.name || foo)) && ((foo || "").toLowerCase() !== "nofilter"));
			}

			return flag;
		}

		private _convertFilterOperator(col: wijmo.grid.IColumn): wijmo.filter.IFilterOperatorEx[]{
			var value = col.filterOperator;

			if (!$.isArray(value)) {
				value = [<any>value];
			}

			value = $.map(value, function (o, i) {
				return (typeof (o) === "string") ? { name: o } : o;
			});

			return value;
		}

		private _convertFilterValue(column: wijmo.grid.IColumn): any[]{
			var value = column.filterValue,
				self = this,
				toStr = function (column: wijmo.grid.IColumn, value: any): any {
					return (value === null || value === undefined)
						? value
						: self.toStr(column, value);
				};
		
			if (!$.isArray(value)) {
				value = [value];
			}

			$.each(value, (i, v1) => {
				if ($.isArray(v1)) {
					$.each(v1, (j, v2) => {
						v1[j] = toStr(column, v2);
					});
				} else {
					value[i] = toStr(column, v1);
				}
			});

			return value;
		}
	}

	class wijgridfilter_options {
		filterOperatorsSortMode = "none";
	}

	wijgridfilter.prototype.options = wijmo.grid.extendWidgetOptions(wijmo.grid.wijgrid.prototype.options, new wijgridfilter_options());

	$.wijmo.registerWidget("wijgridfilter", $.wijmo.wijgrid, wijgridfilter.prototype);
}