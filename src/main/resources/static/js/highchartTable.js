(function(A) {
	A.fn.highchartTable = function() {
		var D = [ "column", "line", "area", "spline", "pie" ];
		var E = function(K, J) {
			var L = A(K).data(J);
			if (typeof L != "undefined") {
				var H = L.split(".");
				var F = window[H[0]];
				for (var G = 1, I = H.length; G < I; G++) {
					F = F[H[G]]
				}
				return F
			}
		};
		this
				.each(function() {
					var W = A(this);
					var v = A(W);
					var s = 1;
					var P = A("caption", W);
					var q = P.length ? A(P[0]).text() : "";
					var o;
					if (v.data("graph-container-before") != 1) {
						var t = v.data("graph-container");
						if (!t) {
							throw "graph-container data attribute is mandatory"
						}
						if (t[0] === "#" || t.indexOf("..") === -1) {
							o = A(t)
						} else {
							var Y = W;
							var S = t;
							while (S.indexOf("..") !== -1) {
								S = S.replace(/^.. /, "");
								Y = Y.parent()
							}
							o = A(S, Y)
						}
						if (o.length !== 1) {
							throw "graph-container is not available in this DOM or available multiple times"
						}
						o = o[0]
					} else {
						v.before("<div></div>");
						o = v.prev();
						o = o[0]
					}
					var h = v.data("graph-type");
					if (!h) {
						throw "graph-type data attribute is mandatory"
					}
					if (A.inArray(h, D) == -1) {
						throw "graph-container data attribute must be one of "
								+ D.join(", ")
					}
					var G = v.data("graph-stacking");
					if (!G) {
						G = "normal"
					}
					var N = v.data("graph-datalabels-enabled");
					var n = v.data("graph-inverted") == 1;
					var X = A("thead th", W);
					var R = [];
					var Z = [];
					var f = 0;
					var e = false;
					X.each(function(AC, w) {
						var AF = A(w);
						var y = AF.data("graph-value-scale");
						var AB = AF.data("graph-type");
						if (A.inArray(AB, D) == -1) {
							AB = h
						}
						var i = AF.data("graph-stack-group");
						if (i) {
							e = true
						}
						var z = AF.data("graph-datalabels-enabled");
						if (typeof z == "undefined") {
							z = N
						}
						var AA = AF.data("graph-yaxis");
						if (typeof AA != "undefined" && AA == "1") {
							s = 2
						}
						var AE = AF.data("graph-skip") == 1;
						if (AE) {
							f = f + 1
						}
						var x = {
							libelle : AF.text(),
							skip : AE,
							indexTd : AC - f - 1,
							color : AF.data("graph-color"),
							visible : !AF.data("graph-hidden"),
							yAxis : typeof AA != "undefined" ? AA : 0,
							dashStyle : AF.data("graph-dash-style") || "solid",
							dataLabelsEnabled : z == 1,
							dataLabelsColor : AF.data("graph-datalabels-color")
									|| v.data("graph-datalabels-color")
						};
						var AD = AF.data("graph-vline-x");
						if (typeof AD == "undefined") {
							x.scale = typeof y != "undefined" ? parseFloat(y)
									: 1;
							x.graphType = AB == "column" && n ? "bar" : AB;
							x.stack = i;
							x.unit = AF.data("graph-unit");
							R[AC] = x
						} else {
							x.x = AD;
							x.height = AF.data("graph-vline-height");
							x.name = AF.data("graph-vline-name");
							Z[AC] = x
						}
					});
					var O = [];
					A(R)
							.each(
									function(w, x) {
										if (w != 0 && !x.skip) {
											var y = {
												name : x.libelle
														+ (x.unit ? " ("
																+ x.unit + ")"
																: ""),
												data : [],
												type : x.graphType,
												stack : x.stack,
												color : x.color,
												visible : x.visible,
												yAxis : x.yAxis,
												dashStyle : x.dashStyle,
												marker : {
													enabled : false
												},
												dataLabels : {
													enabled : x.dataLabelsEnabled,
													color : x.dataLabelsColor,
													align : v
															.data("graph-datalabels-align")
															|| (h == "column"
																	&& n == 1 ? undefined
																	: "center")
												}
											};
											if (x.dataLabelsEnabled) {
												var i = E(W,
														"graph-datalabels-formatter");
												if (i) {
													y.dataLabels.formatter = function() {
														return i(this.y)
													}
												}
											}
											O.push(y)
										}
									});
					A(Z).each(function(i, w) {
						if (typeof w != "undefined" && !w.skip) {
							O.push({
								name : w.libelle,
								data : [ {
									x : w.x,
									y : 0,
									name : w.name
								}, {
									x : w.x,
									y : w.height,
									name : w.name
								} ],
								type : "spline",
								color : w.color,
								visible : w.visible,
								marker : {
									enabled : false
								}
							})
						}
					});
					var g = [];
					var H = E(W, "graph-point-callback");
					var m = v.data("graph-xaxis-type") == "datetime";
					var r = A("tbody:first tr", W);
					r.each(function(w, x) {
						if (!!A(x).data("graph-skip")) {
							return
						}
						var i = A("td", x);
						i.each(function(y, AD) {
							var AK;
							var AE = R[y];
							if (AE.skip) {
								return
							}
							var z = A(AD);
							if (y == 0) {
								AK = z.text();
								g.push(AK)
							} else {
								var AI = z.text();
								var AG = O[AE.indexTd];
								if (AI.length == 0) {
									if (!m) {
										AG.data.push(null)
									}
								} else {
									var AA = AI.replace(/\s/g, "").replace(/,/,
											".");
									AK = Math.round(parseFloat(AA) * AE.scale
											* 100) / 100;
									var AJ = z.data("graph-x");
									if (m) {
										AJ = A("td", A(x)).first().text();
										var AC = C(AJ);
										AJ = AC.getTime()
												- AC.getTimezoneOffset() * 60
												* 1000
									}
									var AF = z.data("graph-name");
									var AB = {
										name : typeof AF != "undefined" ? AF
												: AI,
										y : AK,
										x : AJ
									};
									if (H) {
										AB.events = {
											click : function() {
												return H(this)
											}
										}
									}
									if (AE.graphType === "pie") {
										if (z.data("graph-item-highlight")) {
											AB.sliced = 1
										}
									}
									var AH = z.data("graph-item-color");
									if (typeof AH != "undefined") {
										AB.color = AH
									}
									AG.data.push(AB)
								}
							}
						})
					});
					var M = [];
					var c;
					for (c = 1; c <= s; c++) {
						var K = {
							title : {
								text : typeof v.data("graph-yaxis-" + c
										+ "-title-text") != "undefined" ? v
										.data("graph-yaxis-" + c
												+ "-title-text") : null
							},
							max : typeof v.data("graph-yaxis-" + c + "-max") != "undefined" ? v
									.data("graph-yaxis-" + c + "-max")
									: null,
							min : typeof v.data("graph-yaxis-" + c + "-min") != "undefined" ? v
									.data("graph-yaxis-" + c + "-min")
									: null,
							reversed : v.data("graph-yaxis-" + c + "-reversed") == "1",
							opposite : v.data("graph-yaxis-" + c + "-opposite") == "1",
							tickInterval : v.data("graph-yaxis-" + c
									+ "-tick-interval")
									|| null,
							labels : {
								rotation : v.data("graph-yaxis-" + c
										+ "-rotation") || 0
							},
							startOnTick : v.data("graph-yaxis-" + c
									+ "-start-on-tick") !== "0",
							endOnTick : v.data("graph-yaxis-" + c
									+ "-end-on-tick") !== "0",
							stackLabels : {
								enabled : v.data("graph-yaxis-" + c
										+ "-stacklabels-enabled") == "1"
							},
							gridLineInterpolation : v.data("graph-yaxis-" + c
									+ "-grid-line-interpolation")
									|| null
						};
						var l = E(W, "graph-yaxis-" + c + "-formatter-callback");
						if (l) {
							K.labels.formatter = function() {
								return l(this.value)
							}
						}
						M.push(K)
					}
					var J = [ "#4572A7", "#AA4643", "#89A54E", "#80699B",
							"#3D96AE", "#DB843D", "#92A8CD", "#A47D7C",
							"#B5CA92" ];
					var k = [];
					var U = typeof Highcharts.theme != "undefined"
							&& typeof Highcharts.theme.colors != "undefined" ? Highcharts.theme.colors
							: [];
					var L = v.data("graph-line-shadow");
					var V = v.data("graph-line-width") || 2;
					for (var p = 0; p < 9; p++) {
						var d = "graph-color-" + (p + 1);
						k.push(typeof v.data(d) != "undefined" ? v.data(d)
								: typeof U[p] != "undefined" ? U[p] : J[p])
					}
					var j = v.data("graph-margin-top");
					var u = v.data("graph-margin-right");
					var a = v.data("graph-margin-bottom");
					var Q = v.data("graph-margin-left");
					var b = v.data("graph-xaxis-labels-enabled");
					var I = {};
					var T = v.data("graph-xaxis-labels-font-size");
					if (typeof T != "undefined") {
						I.fontSize = T
					}
					var F = {
						colors : k,
						chart : {
							renderTo : o,
							inverted : n,
							marginTop : typeof j != "undefined" ? j : null,
							marginRight : typeof u != "undefined" ? u : null,
							marginBottom : typeof a != "undefined" ? a : null,
							marginLeft : typeof Q != "undefined" ? Q : null,
							spacingTop : v.data("graph-spacing-top") || 10,
							height : v.data("graph-height") || null,
							zoomType : v.data("graph-zoom-type") || null,
							polar : v.data("graph-polar") || null
						},
						title : {
							text : q
						},
						subtitle : {
							text : v.data("graph-subtitle-text") || ""
						},
						legend : {
							enabled : v.data("graph-legend-disabled") != "1",
							layout : v.data("graph-legend-layout")
									|| "horizontal",
							symbolWidth : v.data("graph-legend-width") || 30,
							x : v.data("graph-legend-x") || 15,
							y : v.data("graph-legend-y") || 0
						},
						xAxis : {
							categories : (v.data("graph-xaxis-type") != "datetime") ? g
									: undefined,
							type : (v.data("graph-xaxis-type") == "datetime") ? "datetime"
									: undefined,
							reversed : v.data("graph-xaxis-reversed") == "1",
							opposite : v.data("graph-xaxis-opposite") == "1",
							showLastLabel : typeof v
									.data("graph-xaxis-show-last-label") != "undefined" ? v
									.data("graph-xaxis-show-last-label")
									: true,
							tickInterval : v.data("graph-xaxis-tick-interval")
									|| null,
							dateTimeLabelFormats : {
								second : "%e. %b",
								minute : "%e. %b",
								hour : "%e. %b",
								day : "%e. %b",
								week : "%e. %b",
								month : "%e. %b",
								year : "%e. %b"
							},
							labels : {
								rotation : v.data("graph-xaxis-rotation") || 0,
								align : v.data("graph-xaxis-align") || "center",
								enabled : typeof b != "undefined" ? b : true,
								style : I
							},
							startOnTick : v.data("graph-xaxis-start-on-tick"),
							endOnTick : v.data("graph-xaxis-end-on-tick"),
							min : B(W, "min"),
							max : B(W, "max"),
							alternateGridColor : v
									.data("graph-xaxis-alternateGridColor")
									|| null,
							title : {
								text : v.data("graph-xaxis-title-text") || null
							},
							gridLineWidth : v
									.data("graph-xaxis-gridLine-width") || 0,
							gridLineDashStyle : v
									.data("graph-xaxis-gridLine-style")
									|| "ShortDot",
							tickmarkPlacement : v
									.data("graph-xaxis-tickmark-placement")
									|| "between",
							lineWidth : v.data("graph-xaxis-line-width") || 0
						},
						yAxis : M,
						tooltip : {
							formatter : function() {
								if (v.data("graph-xaxis-type") == "datetime") {
									return "<b>"
											+ this.series.name
											+ "</b><br/>"
											+ Highcharts.dateFormat("%e. %b",
													this.x) + " : " + this.y
								} else {
									var i = typeof g[this.point.x] != "undefined" ? g[this.point.x]
											: this.point.x;
									if (h === "pie") {
										return "<strong>" + this.series.name
												+ "</strong><br />" + i + " : "
												+ this.point.y
									}
									return "<strong>" + this.series.name
											+ "</strong><br />" + i + " : "
											+ this.point.name
								}
							}
						},
						credits : {
							enabled : false
						},
						plotOptions : {
							line : {
								dataLabels : {
									enabled : true
								},
								lineWidth : V
							},
							area : {
								lineWidth : V,
								shadow : typeof L != "undefined" ? L : true,
								fillOpacity : v.data("graph-area-fillOpacity") || 0.75
							},
							pie : {
								allowPointSelect : true,
								dataLabels : {
									enabled : true
								},
								showInLegend : v
										.data("graph-pie-show-in-legend") == "1",
								size : "80%"
							},
							series : {
								animation : false,
								stickyTracking : false,
								stacking : e ? G : null,
								groupPadding : v.data("graph-group-padding") || 0
							}
						},
						series : O,
						exporting : {
							filename : q.replace(/ /g, "_"),
							buttons : {
								exportButton : {
									menuItems : null,
									onclick : function() {
										this.exportChart()
									}
								}
							}
						}
					};
					v.trigger("highchartTable.beforeRender", F);
					new Highcharts.Chart(F)
				});
		return this
	};
	var B = function(F, D) {
		var G = A(F).data("graph-xaxis-" + D);
		if (typeof G != "undefined") {
			if (A(F).data("graph-xaxis-type") == "datetime") {
				var E = C(G);
				return E.getTime() - E.getTimezoneOffset() * 60 * 1000
			}
			return G
		}
		return null
	};
	var C = function(I) {
		var F = I.split(" ");
		var H = F[0].split("-");
		var G = null;
		var E = null;
		if (F[1]) {
			var D = F[1].split(":");
			G = parseInt(D[0], 10);
			E = parseInt(D[1], 10)
		}
		return new Date(parseInt(H[0], 10), parseInt(H[1], 10) - 1, parseInt(
				H[2], 10), G, E)
	}
})(jQuery);